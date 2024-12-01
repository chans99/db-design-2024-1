package site.my4cut.springboot.core.api.photo.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.controller.v1.ApiV1Controller
import site.my4cut.springboot.core.api.photo.dto.PhotoCountResponse
import site.my4cut.springboot.core.api.photo.dto.PhotoSaveResponse
import site.my4cut.springboot.core.api.photo.dto.PhotoVideoSaveResponse
import site.my4cut.springboot.core.api.photo.dto.PhotoUrlResponse
import site.my4cut.springboot.core.api.photo.usecase.PhotoUseCase
import site.my4cut.springboot.core.api.swagger.PhotoApiDocs

@ApiV1Controller
class PhotoApiController(
    private val photoUseCase: PhotoUseCase
) : PhotoApiDocs {

    @GetMapping("/photos")
    override fun getMy4Cut(
        @RequestParam("photoCode", required = true) photoCode: String,
        @RequestParam("videoCode", required = true) videoCode: String
    ): ResponseEntity<PhotoUrlResponse> {
        val responseBody = photoUseCase.getPhotoByCode(photoCode, videoCode)
        return ResponseEntity.ok().body(responseBody)
    }

    @PostMapping("/photos/with-video")
    override fun savePhotoAndVideo(
        @RequestPart photo: MultipartFile,
        @RequestPart video: MultipartFile,
        @Auth memberId: Long
    ): ResponseEntity<PhotoVideoSaveResponse> {
        val responseBody = photoUseCase.savePhotoAndVideo(photo, video, memberId)
        return ResponseEntity.ok().body(responseBody)
    }

    @PostMapping("/photos")
    override fun savePhoto(
        @RequestPart photo: MultipartFile,
        @Auth memberId: Long
    ) : ResponseEntity<PhotoSaveResponse> {
        val responseBody = photoUseCase.savePhoto(photo, memberId)
        return ResponseEntity.ok().body(responseBody)
    }

    @GetMapping("/photos/count")
    override fun getPhotoCount(): ResponseEntity<PhotoCountResponse> {
        return ResponseEntity.ok(photoUseCase.getPhotoCount())
    }

    @PatchMapping("/photos/count")
    override fun updatePhotoCount(): ResponseEntity<Unit> {
        photoUseCase.updatePhotoCount()
        return ResponseEntity.noContent().build()
    }

}
