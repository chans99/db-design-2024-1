package site.my4cut.springboot.core.api.frame.usecase

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.admin.adapter.AdminMemberChecker
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.core.api.frame.adapter.frame.FrameRegister
import site.my4cut.springboot.core.api.frame.adapter.frame.FrameRemover
import site.my4cut.springboot.core.api.frame.adapter.frame.FrameUploader
import site.my4cut.springboot.core.api.frame.adapter.framekeyword.FrameKeywordFinder
import site.my4cut.springboot.core.api.frame.adapter.framekeyword.FrameKeywordRegister
import site.my4cut.springboot.core.api.frame.dto.FrameRequest
import site.my4cut.springboot.db.frame.FrameKeywordEntity
import site.my4cut.springboot.db.frame.PhotoFrameEntity

@UseCase
@Transactional(readOnly = true)
class AdminFrameUseCase(
    private val adminMemberChecker: AdminMemberChecker,
    private val frameRemover: FrameRemover,
    private val keywordFinder: FrameKeywordFinder,
    private val frameKeywordRegister: FrameKeywordRegister,
    private val frameRegister: FrameRegister,
    private val frameUploader: FrameUploader
) {

    @Transactional
    fun register(adminKey: String, request: FrameRequest, frameImage: MultipartFile, thumbnailImage: MultipartFile) {
        if (!adminMemberChecker.check(adminKey)) {
            throw InvalidRequestException("10300")
        }
        val frameImageUrl = frameUploader.upload(frameImage)
        val thumbnailImageUrl = frameUploader.upload(thumbnailImage)
        val photoFrameEntity = PhotoFrameEntity(
            title = request.title,
            description = request.description,
            imageUrl = request.imageUrl,
            thumbnailImageUrl = request.title,
            isPublic = false
        )
        request.keywords.forEach {
            if (!keywordFinder.existByContent(it.content)) {
                frameKeywordRegister.register(
                    FrameKeywordEntity(
                        content = it.content,
                        isPublic = true // 프레임 스토어에 등록되는 프레임 키워드는 공개
                    )
                )
            } else {
                val keywordEntity = keywordFinder.findByContentOrThrow(it.content)
                keywordEntity.isPublic = true
            }
        }

        frameRegister.register(photoFrameEntity)
    }


    @Transactional
    fun remove(adminKey: String, frameId: Long) {
        if (!adminMemberChecker.check(adminKey)) {
            throw InvalidRequestException("10300")
        }
        frameRemover.remove(frameId)
    }
}