package site.my4cut.springboot.core.api.frame.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.controller.v1.ApiV1Controller
import site.my4cut.springboot.core.api.frame.dto.FrameInfoResponse
import site.my4cut.springboot.core.api.frame.dto.FrameResponse
import site.my4cut.springboot.core.api.frame.usecase.FrameUseCase
import site.my4cut.springboot.core.api.swagger.FrameApiDocs


@ApiV1Controller
class FrameApiController(
    private val frameUseCase: FrameUseCase
) : FrameApiDocs {


    // 프레임 상세 정보 조회
    @GetMapping("/frames/{frameId}")
    override fun getFrameInfo(
        @Auth memberId: Long,
        @PathVariable frameId: Long
    ): ResponseEntity<FrameInfoResponse> {
        return ResponseEntity.ok(frameUseCase.getFrameInfo(memberId, frameId))
    }

    // 프레임 목록 조회
    @GetMapping("/frames")
    override fun getFrameList(
    @Auth memberId: Long,
    @RequestParam page: Int,
    @RequestParam size: Int,
    @RequestParam(required = false) keywordId: Long?,
    ): ResponseEntity<List<FrameResponse>> {
        return ResponseEntity.ok(frameUseCase.getFrameList(memberId, page, size, keywordId))
    }

    @PostMapping("/frames/{frameId}/download")
    override fun downloadFrame(
        @Auth memberId: Long,
        @PathVariable frameId: Long
    ): ResponseEntity<Unit> {
        frameUseCase.downloadFrame(memberId, frameId)
        return ResponseEntity.noContent().build()
    }

}