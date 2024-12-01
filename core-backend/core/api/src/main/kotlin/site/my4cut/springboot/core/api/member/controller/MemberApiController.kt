package site.my4cut.springboot.core.api.member.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.controller.v1.ApiV1Controller
import site.my4cut.springboot.core.api.member.dto.*
import site.my4cut.springboot.core.api.member.usecase.MemberUseCase
import site.my4cut.springboot.core.api.swagger.MemberApiDocs

@ApiV1Controller
class MemberApiController (
    private val memberUseCase: MemberUseCase
) : MemberApiDocs {

    @GetMapping("/members")
    override fun getMemberInfo(@Auth memberId: Long) : ResponseEntity<MemberInfoResponse>  {
        return ResponseEntity.ok(memberUseCase.getMemberInfo(memberId))
    }

    // 회원 탈퇴 -> Hard Delete
    @DeleteMapping("/members")
    override fun withdraw(@Auth memberId: Long) : ResponseEntity<Unit> {
        memberUseCase.withdraw(memberId)
        return ResponseEntity.noContent().build()
    }

    // 닉네임 설정

    @PatchMapping("/members/nickname")
    override fun updateNickname(
        @RequestBody request: NicknameUpdateRequest,
        @Auth memberId: Long
    ) : ResponseEntity<Unit> {
        memberUseCase.updateNickname(memberId, request)
        return ResponseEntity.noContent().build()
    }


    // 광고 적용 여부 수정
    @PatchMapping("/members/ad")
    override fun updateAd(
        @Auth memberId: Long
    ) : ResponseEntity<Unit>{
        memberUseCase.updateAd(memberId)
        return ResponseEntity.noContent().build()
    }

    // 사용자 프리미엄 설정 비밀번호 추가
    @PatchMapping("/members/premium")
    override fun changeMembershipToPremium(
        @Auth memberId: Long,
        @RequestBody request: MemberPremiumCodeRequest
    ) : ResponseEntity<Unit> {
        memberUseCase.updateMembershipToPremium(memberId, request)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/members/premium/cancel")
    override fun changeMembershipToNormal(
        @Auth memberId: Long
    ) : ResponseEntity<Unit> {
        memberUseCase.updateMembershipToNormal(memberId)
        return ResponseEntity.noContent().build()
    }

    // 기본 프레임 활성화 여부 변경
    @PatchMapping("/members/default-frame-activation")
    override fun updateDefaultFrameActivation(
        @Auth memberId: Long,
    ) : ResponseEntity<Unit> {
        memberUseCase.updateDefaultFrameActivation(memberId)
        return ResponseEntity.noContent().build()
    }
}