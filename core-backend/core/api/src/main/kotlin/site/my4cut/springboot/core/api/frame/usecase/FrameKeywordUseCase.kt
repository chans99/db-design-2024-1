package site.my4cut.springboot.core.api.frame.usecase

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.frame.adapter.framekeyword.FrameKeywordFinder
import site.my4cut.springboot.core.api.frame.adapter.framekeyword.PhotoFrameFrameKeywordFinder
import site.my4cut.springboot.core.api.frame.adapter.memberframe.MemberFrameFinder
import site.my4cut.springboot.core.api.frame.dto.FrameKeywordResponse

@UseCase
@Transactional(readOnly = true)
class FrameKeywordUseCase(
    private val keywordFinder: FrameKeywordFinder,
    private val memberFrameFinder: MemberFrameFinder,
    private val photoFrameFrameKeywordFinder: PhotoFrameFrameKeywordFinder
) {
    fun getKeywords(): List<FrameKeywordResponse> {
        return keywordFinder.findAll()
            .map {
                FrameKeywordResponse(
                    frameKeywordId = it.id!!,
                    content = it.content
                )
            }
    }

    fun getMemberFrameKeywords(memberId: Long) : List<FrameKeywordResponse> {
        val responseSet = mutableSetOf<FrameKeywordResponse>()
        val memberFrameEntityList = memberFrameFinder.findByMemberId(memberId)
        memberFrameEntityList.
            forEach {
                photoFrameFrameKeywordFinder.findByPhotoFrameId(it.photoFrameId!!)
                    .forEach {
                        entity ->
                        val keywordEntity = keywordFinder.findByIdOrThrow(entity.frameKeywordId!!)
                        responseSet.add(FrameKeywordResponse(
                            frameKeywordId = keywordEntity.id!!,
                            content = keywordEntity.content
                        ))
                    }
            }
        return responseSet.toList()
    }
}