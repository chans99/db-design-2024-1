package site.my4cut.springboot.core.api.frame.adapter.frame

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.frame.PhotoFrameJpaRepository

@Component
class FrameRemover(
    private val photoFrameJpaRepository: PhotoFrameJpaRepository
) {

    fun remove(id: Long) {
        photoFrameJpaRepository.deleteById(id)
    }

}