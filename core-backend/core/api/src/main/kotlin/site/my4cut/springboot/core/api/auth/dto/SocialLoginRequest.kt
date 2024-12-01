package site.my4cut.springboot.core.api.auth.dto

import io.swagger.v3.oas.annotations.media.Schema
import site.my4cut.springboot.core.enums.member.SocialType

@Schema(name = "소셜 로그인 요청")
data class SocialLoginRequest(
    @Schema(description = "소셜 타입", example = "KAKAO")
    val socialType: SocialType,
    @Schema(description = "소셜 토큰", example = "eysdsaodasd...")
    val token: String
)
