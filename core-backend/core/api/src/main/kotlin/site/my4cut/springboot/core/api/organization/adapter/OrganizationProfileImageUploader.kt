package site.my4cut.springboot.core.api.organization.adapter

import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.config.stereotype.Adapter
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.core.api.photo.adapter.PhotoUploader
import site.my4cut.springboot.infra.aws.S3Service
import java.util.*

@Adapter
class OrganizationProfileImageUploader(
    private val s3Service: S3Service
) {
    companion object {
        private const val PROFILE_IMAGE_DIRECTORY_PATH = "organization-image/"
    }

    fun upload(userCode: String, image: MultipartFile): String {
        val key = UUID.randomUUID().toString()
        val uploadUrl = "${PROFILE_IMAGE_DIRECTORY_PATH}$userCode/$key.${extractImageExtension(image)}"
        return s3Service.uploadFile(uploadUrl, image)
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
}