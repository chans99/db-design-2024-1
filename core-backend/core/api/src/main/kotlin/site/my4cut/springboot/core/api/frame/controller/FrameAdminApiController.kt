package site.my4cut.springboot.core.api.frame.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.controller.v1.AdminApiV1Controller
import site.my4cut.springboot.core.api.frame.dto.FrameRequest
import site.my4cut.springboot.core.api.frame.usecase.AdminFrameUseCase
import site.my4cut.springboot.core.api.swagger.AdminFrameApiDocs

@AdminApiV1Controller
class FrameAdminApiController(
    private val adminFrameUseCase: AdminFrameUseCase
) : AdminFrameApiDocs {

    @DeleteMapping("/frames/{frameId}")
    override fun removeFrame(
        @PathVariable frameId: Long,
        @RequestHeader("Aespa") adminKey: String
    ) : ResponseEntity<Unit> {
        adminFrameUseCase.remove(adminKey, frameId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/frames")
    override fun registerFrame(
        @RequestHeader("Aespa") adminKey: String,
        @RequestPart frameImage: MultipartFile,
        @RequestPart frameThumbnail: MultipartFile,
        request: FrameRequest
    ) : ResponseEntity<Unit> {
        adminFrameUseCase.register(adminKey, request, frameImage, frameThumbnail)
        return ResponseEntity.noContent().build()
    }


}