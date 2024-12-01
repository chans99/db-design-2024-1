package site.my4cut.springboot.core.api.photo.adapter

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.infra.aws.S3Service
import java.util.*

@Component
class PhotoUploader(
    private val s3Service: S3Service
) {

    companion object {
        private const val PHOTO_DIRECTORY_PATH = "my4cuts-photo/"
        private const val VIDEO_DIRECTORY_PATH = "my4cuts-video/"
    }

    // TODO: S3에 이미지 업로드
    fun uploadPhoto(userCode: String, image: MultipartFile): String {
        val key = UUID.randomUUID().toString()
        val uploadUrl = "$PHOTO_DIRECTORY_PATH$userCode/$key.${extractImageExtension(image)}"
        return s3Service.uploadFile(uploadUrl, image)
    }

    fun uploadVideo(userCode: String, video: MultipartFile): String {
        val key = UUID.randomUUID().toString()
        val uploadUrl = "$VIDEO_DIRECTORY_PATH$userCode/$key.${extractVideoExtension(video)}"
        return s3Service.uploadFile(uploadUrl, video)
    }

    private fun extractImageExtension(image: MultipartFile): String {
        return when (image.contentType) {
            "image/jpeg" -> "jpeg"
            "image/png" -> "png"
            "image/gif" -> "gif"
            "image/bmp" -> "bmp"
            else -> throw InvalidRequestException("지원하지 않는 이미지 형식입니다.")
        }
    }

    private fun extractVideoExtension(video: MultipartFile): String {
        return when (video.contentType) {
            "video/mp4" -> "mp4"
            else -> throw InvalidRequestException("지원하지 않는 비디오 형식입니다.")
        }
    }


}