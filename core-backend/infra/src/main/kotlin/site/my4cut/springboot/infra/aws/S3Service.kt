package site.my4cut.springboot.infra.aws

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import site.my4cut.springboot.infra.exception.S3Exception
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.DeleteObjectPresignRequest
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest
import java.net.URL
import java.time.Duration

@Component
class S3Service(
    @Value("\${aws-property.bucket-name}")
    private val bucketName: String,
    @Value("\${aws-property.cloudfront-url}")
    private val cloudFrontUrl: String,
    private val s3Client: S3Client
) {

    private val region: Region = Region.of(Region.AP_NORTHEAST_2.toString())
    private final val presignedUrlExpireMinute: Long = 60
    private val presigner = S3Presigner.builder().region(Region.AP_NORTHEAST_2).build()
    private val restClient = RestClient.create()
    private val webClient = WebClient.builder()
        .clientConnector(ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
        .build()

    fun uploadFile(key: String, multipartFile: MultipartFile) : String {
        try {
            val putObjectResponse = s3Client.putObject(
                PutObjectRequest.builder()
                    .contentType(multipartFile.contentType)
                    .contentDisposition("inline")
                    .bucket(bucketName)
                    .key(key)
                    .build(),
                    RequestBody.fromInputStream(multipartFile.inputStream, multipartFile.size)
            )
            return "$cloudFrontUrl/$key"
        } catch (e: Exception) {
            throw S3Exception("S3 Object 업로드 실패")
        }
    }

    // Presigned Url을 사용하여 파일 삭제
    fun delete(fileUrl: String) {
        try {
            val key = fileUrl.replace("$cloudFrontUrl/", "")
            s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(key).build())
        } catch (e: Exception) {
            throw S3Exception("S3 이미지 삭제 실패")
        }

    }

    private fun getUploadPresignedUrl(key: String) : URL {
        val putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build()
        val presignedUrlRequest = PutObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(presignedUrlExpireMinute))
            .putObjectRequest(putObjectRequest)
            .build()
        return presigner.presignPutObject(presignedUrlRequest).url()
    }

    private fun getDeletePresignerUrl(key: String): URL? {
        val deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build()
        val presignedUrlRequest = DeleteObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(presignedUrlExpireMinute))
            .deleteObjectRequest(deleteObjectRequest)
            .build()
        return presigner.presignDeleteObject(presignedUrlRequest).url()
    }
}