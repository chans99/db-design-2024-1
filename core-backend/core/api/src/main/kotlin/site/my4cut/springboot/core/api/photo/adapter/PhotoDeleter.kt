package site.my4cut.springboot.core.api.photo.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.infra.aws.S3Service

@Component
class PhotoDeleter(
    private val s3Service: S3Service
) {
    fun delete(key: String) {
        s3Service.delete(key)
    }
}