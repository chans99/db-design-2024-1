package site.my4cut.springboot.core.api.photo.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.CoreException
import site.my4cut.springboot.core.api.exception.EntityNotFoundException
import site.my4cut.springboot.db.photo.PhotoEntity
import site.my4cut.springboot.db.photo.PhotoJpaRepository

@Component
class PhotoFinder(
    val photoJpaRepository: PhotoJpaRepository
) {

    fun findByPhotoCode(photoCode: String) : PhotoEntity {
        return photoJpaRepository.findByPhotoCode(photoCode)
            ?: throw EntityNotFoundException("존재하지 않는 사진입니다. photoCode : $photoCode")
    }

    fun findAllByMemberId(memberId: Long) : List<PhotoEntity> {
        return photoJpaRepository.findAllByMemberId(memberId)
    }
    fun findByIdOrThrow(id: Long) : PhotoEntity {
        return photoJpaRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException("존재하지 않는 사진입니다. id : $id")
            }
    }

    fun countAllPhotos() : Long {
        return photoJpaRepository.count()
    }
}