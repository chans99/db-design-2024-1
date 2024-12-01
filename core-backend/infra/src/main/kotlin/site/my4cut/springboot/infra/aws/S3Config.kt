package site.my4cut.springboot.infra.aws

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner

@Configuration
class S3Config(
    @Value("\${aws-property.access-key}") private val accessKey: String,
    @Value("\${aws-property.secret-key}") private val secretKey: String,
    @Value("\${aws-property.aws-region}") private val regionString: String
) {

    companion object {
        const val AWS_ACCESS_KEY_ID = "aws.accessKeyId"
        const val AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey"
    }

    @Bean
    fun systemPropertyCredentialsProvider(): SystemPropertyCredentialsProvider {
        System.setProperty(AWS_ACCESS_KEY_ID, accessKey)
        System.setProperty(AWS_SECRET_ACCESS_KEY, secretKey)
        return SystemPropertyCredentialsProvider.create()
    }

    @Bean
    fun getRegion(): Region {
        return Region.of(regionString)
    }

    @Bean
    fun getS3Client(): S3Client {
        return S3Client.builder()
            .region(getRegion())
            .credentialsProvider(systemPropertyCredentialsProvider())
            .build()
    }

    @Bean
    fun getPresigner(): S3Presigner {
        return S3Presigner.builder()
            .credentialsProvider(systemPropertyCredentialsProvider())
            .region(getRegion())
            .build()
    }
}