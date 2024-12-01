package site.my4cut.springboot.core.api.frame.adapter.framekeyword

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.frame.PhotoFrameFrameKeywordEntity
import site.my4cut.springboot.db.frame.PhotoFrameFrameKeywordJpaRepository

@Component
class PhotoFrameFrameKeywordRegister(
    private val photoFrameFrameKeywordJpaRepository: PhotoFrameFrameKeywordJpaRepository
) {
    fun register(entity: PhotoFrameFrameKeywordEntity) : PhotoFrameFrameKeywordEntity {
        return photoFrameFrameKeywordJpaRepository.save(entity)

    }
}