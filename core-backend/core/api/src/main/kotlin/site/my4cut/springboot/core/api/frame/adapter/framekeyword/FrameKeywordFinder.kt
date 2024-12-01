package site.my4cut.springboot.core.api.frame.adapter.framekeyword

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.CoreException
import site.my4cut.springboot.core.api.exception.EntityNotFoundException
import site.my4cut.springboot.db.frame.FrameKeywordEntity
import site.my4cut.springboot.db.frame.FrameKeywordJpaRepository
import site.my4cut.springboot.db.frame.PhotoFrameFrameKeywordJpaRepository

@Component
class FrameKeywordFinder(
    private val frameKeywordJpaRepository: FrameKeywordJpaRepository,
    private val photoFrameFrameKeywordJpaRepository: PhotoFrameFrameKeywordJpaRepository
) {

    fun existByContent(content: String) : Boolean {
        return frameKeywordJpaRepository.existsByContent(content)
    }

    fun findAll() : List<FrameKeywordEntity> {
        return frameKeywordJpaRepository.findAll()
    }

    fun findByIdOrThrow(id: Long) : FrameKeywordEntity {
        return frameKeywordJpaRepository.findById(id)
            .orElseThrow {
                EntityNotFoundException("존재하지 않는 키워드입니다. 요청 id : $id")
            }
    }

    fun findByFrameIdOrThrow(frameId : Long) : List<FrameKeywordEntity>{
        return photoFrameFrameKeywordJpaRepository.findByPhotoFrameId(frameId)
            .map {
                frameKeywordJpaRepository.findById(it.frameKeywordId!!)
                    .orElseThrow {
                        EntityNotFoundException("존재하지 않는 키워드입니다. 요청 id : ${it.frameKeywordId}")
                    }
            }
    }

    fun findByContentOrThrow(content: String) : FrameKeywordEntity {
        return frameKeywordJpaRepository.findByContent(content)
            ?: throw EntityNotFoundException("존재하지 않는 키워드입니다. 요청 content : $content")
    }

    fun existByFrameIdAndKeywordId(frameId: Long, keywordId: Long) : Boolean {
        return photoFrameFrameKeywordJpaRepository.existsByPhotoFrameIdAndFrameKeywordId(frameId, keywordId)
    }

}