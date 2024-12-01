package site.my4cut.springboot.core.api.member.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "회원 프리미엄 코드 요청")
data class MemberPremiumCodeRequest(
    @Schema(description = "프리미엄 코드", example = "1234567890")
    val premiumCode: String
)
