package site.my4cut.springboot.core.api.photo.usecase

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.member.adapter.MemberFinder
import site.my4cut.springboot.core.api.photo.adapter.*
import site.my4cut.springboot.core.api.photo.dto.*
import site.my4cut.springboot.db.photo.PhotoEntity
import java.util.UUID

@UseCase
@Transactional(readOnly = true)
class PhotoUseCase(
    private val photoSaver: PhotoSaver,
    private val photoFinder: PhotoFinder,
    private val photoUploader: PhotoUploader,
    private val memberFinder: MemberFinder,
    private val photoLogFinder: PhotoLogFinder,
    private val photoLogUpdater: PhotoLogUpdater
) {
    fun getPhotoByCode(photoCode: String, videoCode: String) : PhotoUrlResponse {
        val photoEntity = photoFinder.findByPhotoCode(photoCode)
        return PhotoUrlResponse(
            photoUrl = photoEntity.imageUrl,
            videoUrl = photoEntity.videoUrl
        )
    }

    fun getPhotoList(memberId: Long) : List<PhotoResponse> {
        return photoFinder.findAllByMemberId(memberId)
            .map { PhotoResponse(
                photoId = it.id!!,
                imageUrl = it.imageUrl!!
            ) }

    }

    fun getPhotoCount() : PhotoCountResponse {
        return PhotoCountResponse(
            photoLogFinder.findAll()[0].photoCount
        )
    }

    @Transactional
    fun savePhotoAndVideo(photo:MultipartFile, video: MultipartFile, memberId: Long) : PhotoVideoSaveResponse {
        val memberEntity = memberFinder.findByIdOrThrow(memberId)
        val imageUrl = photoUploader.uploadPhoto(memberEntity.userCode.toString(), photo)
        val videoUrl = photoUploader.uploadVideo(memberEntity.userCode.toString(), video)
        val photoEntity = PhotoEntity(
            memberId = memberId,
            imageUrl = imageUrl,
            videoUrl = videoUrl,
            photoCode = UUID.randomUUID().toString(),
            videoCode = UUID.randomUUID().toString()
        )
        photoSaver.save(photoEntity)
        return PhotoVideoSaveResponse(
            photoCode = photoEntity.photoCode,
            videoCode = photoEntity.videoCode
        )
    }

    @Transactional
    fun savePhoto(photo:MultipartFile, memberId: Long) : PhotoSaveResponse {
        val memberEntity = memberFinder.findByIdOrThrow(memberId)
        val imageUrl = photoUploader.uploadPhoto(memberEntity.userCode.toString(), photo)
        val photoEntity = PhotoEntity(
            memberId = memberId,
            imageUrl = imageUrl,
            photoCode = UUID.randomUUID().toString(),
            videoCode = UUID.randomUUID().toString()
        )
        photoSaver.save(photoEntity)
        return PhotoSaveResponse(
            photoCode = photoEntity.photoCode
        )
    }

    @Transactional
    fun updatePhotoCount() {
        photoLogUpdater.update(photoLogFinder.findAll()[0])
    }
}