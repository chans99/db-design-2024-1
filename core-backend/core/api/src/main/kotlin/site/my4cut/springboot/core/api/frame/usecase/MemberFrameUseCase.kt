package site.my4cut.springboot.core.api.frame.usecase

import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.frame.adapter.frame.FrameFinder
import site.my4cut.springboot.core.api.frame.adapter.frame.FrameRegister
import site.my4cut.springboot.core.api.frame.adapter.framekeyword.FrameKeywordFinder
import site.my4cut.springboot.core.api.frame.adapter.framekeyword.FrameKeywordRegister
import site.my4cut.springboot.core.api.frame.adapter.framekeyword.PhotoFrameFrameKeywordRegister
import site.my4cut.springboot.core.api.frame.adapter.memberframe.MemberFrameCounter
import site.my4cut.springboot.core.api.frame.adapter.memberframe.MemberFrameFinder
import site.my4cut.springboot.core.api.frame.adapter.memberframe.MemberFrameRegister
import site.my4cut.springboot.core.api.frame.adapter.memberframe.MemberFrameRemover
import site.my4cut.springboot.core.api.frame.dto.FrameKeywordResponse
import site.my4cut.springboot.core.api.frame.dto.MemberFrameRegisterRequest
import site.my4cut.springboot.core.api.frame.dto.MemberFrameRequest
import site.my4cut.springboot.core.api.frame.dto.MemberOwnedFrameResponse
import site.my4cut.springboot.db.frame.FrameKeywordEntity
import site.my4cut.springboot.db.frame.MemberPhotoFrameEntity
import site.my4cut.springboot.db.frame.PhotoFrameEntity
import site.my4cut.springboot.db.frame.PhotoFrameFrameKeywordEntity

@UseCase
@Transactional(readOnly = true)
class MemberFrameUseCase(
    private val memberFrameFinder: MemberFrameFinder,
    private val memberFrameRemover: MemberFrameRemover,
    private val frameFinder: FrameFinder,
    private val frameKeywordFinder: FrameKeywordFinder,
    private val frameRegister: FrameRegister,
    private val photoFrameFrameKeywordRegister: PhotoFrameFrameKeywordRegister,
    private val photoFrameFrameKeywordFinder: FrameKeywordFinder,
    private val frameKeywordRegister: FrameKeywordRegister,
    private val memberFrameCounter: MemberFrameCounter,
    private val memberFrameRegister: MemberFrameRegister
) {

    fun findAllByMemberId(memberId: Long, keywordId: Long?): List<MemberOwnedFrameResponse> {
        if (keywordId == null) {
            return memberFrameFinder.findByMemberId(memberId)
                .map {
                    val frameEntity = frameFinder.findByIdOrThrow(it.photoFrameId!!)
                    val keywordResponseList = frameKeywordFinder.findByFrameIdOrThrow(frameEntity.id!!)
                        .map { k ->
                            FrameKeywordResponse(
                                frameKeywordId = k.id!!,
                                content = k.content
                            )
                        }
                    MemberOwnedFrameResponse(
                        memberPhotoFrameId = it.id!!,
                        makerName = frameEntity.makerName,
                        frameId = frameEntity.id!!,
                        imageUrl = frameEntity.imageUrl,
                        thumbnailImageUrl = frameEntity.thumbnailImageUrl,
                        isPublic = frameEntity.isPublic,
                        frameOrder = it.frameOrder,
                        keywords = keywordResponseList
                    )
                }
        }

        val filteredMemberFrameList = memberFrameFinder.findByMemberId(memberId)
            .filter {
                val frameEntity = frameFinder.findByIdOrThrow(it.photoFrameId!!)
                photoFrameFrameKeywordFinder.existByFrameIdAndKeywordId(frameEntity.id!!, keywordId)
            }
        return filteredMemberFrameList.map {
            val frameEntity = frameFinder.findByIdOrThrow(it.photoFrameId!!)
            val frameKeywordEntityList = frameKeywordFinder.findByFrameIdOrThrow(frameEntity.id!!)

            val keywordResponseList = frameKeywordEntityList
                .map { k ->
                    FrameKeywordResponse(
                        frameKeywordId = k.id!!,
                        content = k.content
                    )
                }
            MemberOwnedFrameResponse(
                memberPhotoFrameId = it.id!!,
                makerName = frameEntity.makerName,
                frameId = frameEntity.id!!,
                imageUrl = frameEntity.imageUrl,
                thumbnailImageUrl = frameEntity.thumbnailImageUrl,
                isPublic = frameEntity.isPublic,
                frameOrder = it.frameOrder,
                keywords = keywordResponseList
            )
        }
    }

    @Transactional
    fun deleteBy(memberId: Long, memberFrameId: Long) {
        // 사용자 프레임 삭제
        memberFrameRemover.remove(memberId, memberFrameId)
    }

    @Transactional
    fun changeOrder(memberId: Long, request: List<MemberFrameRequest>) {
        request.forEach {
            memberFrameFinder.findByIdOrThrow(it.memberFrameId)
                .apply {
                    frameOrder = it.frameOrder
                }
        }
    }

    @Transactional
    fun register(memberId: Long, request: MemberFrameRegisterRequest) {
        val frameEntity = PhotoFrameEntity(
            title = request.title,
            isPublic = false,
            makerName = request.makerName,
            description = request.description,
            imageUrl = request.imageUrl,
            thumbnailImageUrl = request.thumbnailImageUrl
        )
        val savedFrameEntity = frameRegister.register(frameEntity)

        request.keywords
            .forEach {
                if (frameKeywordFinder.existByContent(it.content)) {
                    val entity = PhotoFrameFrameKeywordEntity(
                        photoFrameId = savedFrameEntity.id,
                        frameKeywordId = frameKeywordFinder.findByContentOrThrow(it.content).id
                    )
                    photoFrameFrameKeywordRegister.register(entity)
                } else {
                    val savedKeywordEntity = frameKeywordRegister.register(
                        FrameKeywordEntity(
                            content = it.content,
                            isPublic = false
                        )
                    )
                    val entity = PhotoFrameFrameKeywordEntity(
                        photoFrameId = savedFrameEntity.id,
                        frameKeywordId = savedKeywordEntity.id
                    )
                    photoFrameFrameKeywordRegister.register(entity)
                }
            }

        val memberPhotoFrameEntity = MemberPhotoFrameEntity(
            memberId = memberId,
            photoFrameId = savedFrameEntity.id,
            frameOrder = memberFrameCounter.countByMemberId(memberId)
        )

        memberFrameRegister.register(memberPhotoFrameEntity)
    }
}