package site.my4cut.springboot.core.api.frame.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.controller.v1.ApiV1Controller
import site.my4cut.springboot.core.api.frame.dto.FrameKeywordResponse
import site.my4cut.springboot.core.api.frame.usecase.FrameKeywordUseCase
import site.my4cut.springboot.core.api.swagger.FrameKeywordApiDocs

@ApiV1Controller
class FrameKeywordApiController(
    private val frameKeywordUseCase: FrameKeywordUseCase
) : FrameKeywordApiDocs {
    @GetMapping("/frames/keywords")
    override fun getFrameKeyword(@Auth memberId: Long): ResponseEntity<List<FrameKeywordResponse>> {
        return ResponseEntity.ok(frameKeywordUseCase.getKeywords())
    }

    @GetMapping("/members/frames/keywords")
    override fun getUserFrameKeywordUser(@Auth memberId: Long): ResponseEntity<List<FrameKeywordResponse>> {
        return ResponseEntity.ok(frameKeywordUseCase.getMemberFrameKeywords(memberId))
    }
}