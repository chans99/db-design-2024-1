package site.my4cut.springboot.core.api.frame.usecase

import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.exception.AccessDeniedException
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.core.api.frame.adapter.frame.FrameFinder
import site.my4cut.springboot.core.api.frame.adapter.frame.FrameUpdater
import site.my4cut.springboot.core.api.frame.adapter.framekeyword.FrameKeywordFinder
import site.my4cut.springboot.core.api.frame.adapter.memberframe.MemberFrameCounter
import site.my4cut.springboot.core.api.frame.adapter.memberframe.MemberFrameFinder
import site.my4cut.springboot.core.api.frame.adapter.memberframe.MemberFrameRegister
import site.my4cut.springboot.core.api.frame.dto.FrameInfoResponse
import site.my4cut.springboot.core.api.frame.dto.FrameKeywordResponse
import site.my4cut.springboot.core.api.frame.dto.FrameResponse
import site.my4cut.springboot.db.frame.MemberPhotoFrameEntity

@UseCase
@Transactional(readOnly = true)
class FrameUseCase(
    private val frameFinder: FrameFinder,
    private val keywordFinder: FrameKeywordFinder,
    private val frameUpdater: FrameUpdater,
    private val memberPhotoFrameCounter: MemberFrameCounter,
    private val memberPhotoFrameFinder: MemberFrameFinder,
    private val memberFrameRegister: MemberFrameRegister,
) {


    fun getFrameList(memberId: Long, page: Int, size: Int, keywordId: Long?): List<FrameResponse> {
        if (keywordId != null) {
            val frameEntityList = frameFinder.findAllBy(page, size, keywordId)
            frameEntityList.sortedBy {
                it.createdDate
            }
            return frameEntityList.map {
                val keywordEntityList = keywordFinder.findByFrameIdOrThrow(it.id!!)
                val isMemberOwned = memberPhotoFrameFinder.existsByMemberIdAndPhotoFrameId(memberId, it.id!!)
                FrameResponse(
                    id = it.id!!,
                    imageUrl = it.imageUrl,
                    createdAt = it.createdDate,
                    title = it.title,
                    makerName = it.makerName,
                    isMemberOwned = isMemberOwned,
                    keywords = keywordEntityList.map { k ->
                        FrameKeywordResponse(
                            frameKeywordId = k.id!!,
                            content = k.content
                        )
                    }
                )
            }
        }

        val frameEntityList = frameFinder.findAllBy(page, size)
        frameEntityList.sortedBy {
            it.createdDate
        }
        return frameEntityList.map {
            val keywordEntityList = keywordFinder.findByFrameIdOrThrow(it.id!!)
            val isMemberOwned = memberPhotoFrameFinder.existsByMemberIdAndPhotoFrameId(memberId, it.id!!)
            FrameResponse(
                id = it.id!!,
                imageUrl = it.imageUrl,
                createdAt = it.createdDate,
                title = it.title,
                makerName = it.makerName,
                isMemberOwned = isMemberOwned,
                keywords = keywordEntityList.map { k ->
                    FrameKeywordResponse(
                        frameKeywordId = k.id!!,
                        content = k.content
                    )
                }
            )
        }
    }

    @Transactional
    fun getFrameInfo(memberId: Long, frameId: Long): FrameInfoResponse {
        val frameEntity = frameFinder.findByIdOrThrow(frameId)
        if (!frameEntity.isPublic) {
            throw AccessDeniedException("frame is not public")
        }

        // 조회수 증가
        frameEntity.increaseViewCount()
        val keywordEntityList = keywordFinder.findByFrameIdOrThrow(frameId)
        val isMemberOwned = memberPhotoFrameFinder.existsByMemberIdAndPhotoFrameId(memberId, frameId)
        return FrameInfoResponse(
            id = frameId,
            title = frameEntity.title,
            description = frameEntity.description!!,
            imageUrl = frameEntity.imageUrl!!,
            thumbnailImageUrl = frameEntity.thumbnailImageUrl,
            makerName = frameEntity.makerName,
            downloadCount = frameEntity.downloadCount,
            viewCount = frameEntity.viewCount,
            isMemberOwned = isMemberOwned,
            keywords = keywordEntityList.map {
                FrameKeywordResponse(
                    frameKeywordId = it.id!!,
                    content = it.content
                )
            }
        )
    }

    @Transactional
    fun downloadFrame(memberId: Long, frameId: Long) {
        if (memberPhotoFrameFinder.existsByMemberIdAndPhotoFrameId(memberId, frameId)) {
            throw InvalidRequestException("10411")
        }
        val frameEntity = frameFinder.findByIdOrThrow(frameId)
        frameUpdater.increaseDownloadCount(frameEntity)
        val memberPhotoFrameEntity = MemberPhotoFrameEntity(
            memberId = memberId,
            photoFrameId = frameEntity.id,
            frameOrder = memberPhotoFrameCounter.countByMemberId(memberId) + 1
        )
        memberFrameRegister.register(memberPhotoFrameEntity)

    }


}