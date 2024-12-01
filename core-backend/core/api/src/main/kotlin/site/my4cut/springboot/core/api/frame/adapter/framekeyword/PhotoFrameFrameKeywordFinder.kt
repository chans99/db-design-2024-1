package site.my4cut.springboot.core.api.frame.adapter.framekeyword

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.frame.PhotoFrameFrameKeywordEntity
import site.my4cut.springboot.db.frame.PhotoFrameFrameKeywordJpaRepository


@Component
class PhotoFrameFrameKeywordFinder(
    private val photoFrameFrameKeywordJpaRepository: PhotoFrameFrameKeywordJpaRepository
) {
    fun findByFrameKeywordId(frameKeywordId: Long) : List<PhotoFrameFrameKeywordEntity> {
        return photoFrameFrameKeywordJpaRepository.findByFrameKeywordId(frameKeywordId)
    }

    fun findByPhotoFrameId(photoFrameId: Long) : List<PhotoFrameFrameKeywordEntity> {
        return photoFrameFrameKeywordJpaRepository.findByPhotoFrameId(photoFrameId)
    }
}