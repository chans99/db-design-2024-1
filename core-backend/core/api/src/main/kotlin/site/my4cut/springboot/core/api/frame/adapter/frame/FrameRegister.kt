package site.my4cut.springboot.core.api.frame.adapter.frame

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.frame.PhotoFrameEntity
import site.my4cut.springboot.db.frame.PhotoFrameJpaRepository

@Component
class FrameRegister(
    private val photoFrameJpaRepository: PhotoFrameJpaRepository
) {
    fun register(photoFrameEntity: PhotoFrameEntity) : PhotoFrameEntity {
        return photoFrameJpaRepository.save(photoFrameEntity)
    }
}