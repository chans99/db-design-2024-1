package site.my4cut.springboot.core.api.photo.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.photo.PhotoLogEntity
import site.my4cut.springboot.db.photo.PhotoLogJpaRepository

@Component
class PhotoLogFinder(
    private val photoLogJpaRepository: PhotoLogJpaRepository
) {

    fun count(): Long {
        return photoLogJpaRepository.count()
    }

    fun findAll(): List<PhotoLogEntity> {
        return photoLogJpaRepository.findAll()
    }
}