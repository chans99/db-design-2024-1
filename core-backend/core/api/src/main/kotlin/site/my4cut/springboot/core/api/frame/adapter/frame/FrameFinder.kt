package site.my4cut.springboot.core.api.frame.adapter.frame

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.EntityNotFoundException
import site.my4cut.springboot.db.frame.PhotoFrameEntity
import site.my4cut.springboot.db.frame.PhotoFrameFrameKeywordJpaRepository
import site.my4cut.springboot.db.frame.PhotoFrameJpaRepository

@Component
class FrameFinder(
    private val photoFrameJpaRepository: PhotoFrameJpaRepository,
    private val photoFrameFrameKeywordJpaRepository: PhotoFrameFrameKeywordJpaRepository
) {

    fun findAllBy(page: Int, size: Int, keywordId: Long?) : List<PhotoFrameEntity> {
        if (keywordId != null) {
            val frameIdList = photoFrameFrameKeywordJpaRepository.findByFrameKeywordId(keywordId)
                .map {
                    it.photoFrameId
                }
            val pageRequest = PageRequest.of(page, size)
            return photoFrameJpaRepository.findByIsPublicIsTrueAndIdIn(frameIdList, pageRequest)
        }

        return photoFrameJpaRepository.findByIsPublicIsTrue(PageRequest.of(page, size))
    }

    fun findAllBy(page: Int, size: Int) : List<PhotoFrameEntity> {
        val pageRequest = PageRequest.of(page, size)
        return photoFrameJpaRepository.findByIsPublicIsTrue(pageRequest)
    }

    fun findByIdOrThrow(id: Long) : PhotoFrameEntity {
        return photoFrameJpaRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException("존재하지 않는 프레임입니다.")
            }
    }

    fun count() : Long {
        return photoFrameJpaRepository.count()
    }
}