package site.my4cut.springboot.core.api.member.dto

import io.swagger.v3.oas.annotations.media.Schema
import site.my4cut.springboot.core.enums.member.MembershipLevel
import site.my4cut.springboot.core.enums.member.SocialType

@Schema(name = "사용자 정보 응답")
data class MemberInfoResponse(
    @Schema(description = "닉네임", example = "마이네컷")
    val nickname: String?,
    @Schema(description = "사용자 이메일", example = "my4cut@gmail.com")
    val email: String?,
    @Schema(description = "소셜 타입")
    val socialType: SocialType,
    @Schema(description = "단체 여부", example = "True")
    val isOrganizationRegistered: Boolean,
    @Schema(description = "광고 신청 여부", example = "True")
    val isAdApplied: Boolean,
    @Schema(description = "단체명", example = "마이네컷")
    val organizationName: String?,
    @Schema(description = "단체 로고 이미지 URL.. 개인일 경우 빈문자열 반환 ", example = "https://my4cut.site/logo.png")
    val logoImageUrl: String?,
    @Schema(description = "회원 등급 NORMAL, PREMIUM, PREMIUM2", example = "PREMIUM")
    val membershipLevel: MembershipLevel,
    @Schema(description = "기본 프레임 활성화 여부", example = "010-1234-5678")
    val isDefaultFrameActivated: Boolean,
)
