package site.my4cut.springboot.core.api.frame.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.controller.v1.ApiV1Controller
import site.my4cut.springboot.core.api.frame.dto.MemberFrameRegisterRequest
import site.my4cut.springboot.core.api.frame.dto.MemberFrameRequest
import site.my4cut.springboot.core.api.frame.dto.MemberOwnedFrameResponse
import site.my4cut.springboot.core.api.frame.usecase.MemberFrameUseCase
import site.my4cut.springboot.core.api.swagger.MemberFrameApiDocs

@ApiV1Controller
class MemberFrameApiController(
    private val memberFrameUseCase: MemberFrameUseCase
) : MemberFrameApiDocs {

    @GetMapping("/members/frames")
    override fun getMemberFrames(
        @Auth memberId: Long,
        @RequestParam keywordId: Long?
    ): ResponseEntity<List<MemberOwnedFrameResponse>> {
        return ResponseEntity.ok(memberFrameUseCase.findAllByMemberId(memberId, keywordId))
    }

    // 삭제
    @DeleteMapping("/members/frames/{memberFrameId}")
    override fun deleteFrame(
        @Auth memberId: Long,
        @PathVariable memberFrameId: Long
    ): ResponseEntity<Unit> {
        memberFrameUseCase.deleteBy(memberId, memberFrameId)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/members/frames/order")
    override fun changeFrameOrder(
        @Auth memberId: Long,
        @RequestBody request: List<MemberFrameRequest>
    ): ResponseEntity<Unit> {
        memberFrameUseCase.changeOrder(memberId, request)
        return ResponseEntity.noContent().build()
    }


    @PostMapping("/members/frames")
    override fun registerMemberFrame(
        @Auth memberId: Long,
        @RequestBody request: MemberFrameRegisterRequest
    ): ResponseEntity<Unit> {
        memberFrameUseCase.register(memberId, request)
        return ResponseEntity.noContent().build()
    }
}