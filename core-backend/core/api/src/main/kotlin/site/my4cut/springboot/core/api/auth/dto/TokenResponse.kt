package site.my4cut.springboot.core.api.auth.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "토큰 응답")
data class TokenResponse(
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxNjMxNjM4MCwiZXhwIjoxNjE2MzE2NDAwfQ")
    val accessToken: String,
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxNjMxNjM4MCwiZXhwIjoxNjE2MzE2NDAwfQ")
    val refreshToken: String
) {
}