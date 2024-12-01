package site.my4cut.springboot.core.api.photo.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.photo.PhotoEntity
import site.my4cut.springboot.db.photo.PhotoJpaRepository

@Component
class PhotoSaver(
    private val photoJpaRepository: PhotoJpaRepository
) {
    fun save(photo: PhotoEntity) {
        photoJpaRepository.save(photo)
    }
}