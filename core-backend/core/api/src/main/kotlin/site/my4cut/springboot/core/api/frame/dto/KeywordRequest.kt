package site.my4cut.springboot.core.api.frame.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "키워드 요청")
data class KeywordRequest(
    @Schema(description = "키워드 내용")
    val content: String
) {
}