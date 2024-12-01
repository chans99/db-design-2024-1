package site.my4cut.springboot.core.api.frame.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "프레임 키워드 응답")
data class FrameKeywordResponse(
    @Schema(description = "프레임 키워드 id", example = "1")
    val frameKeywordId: Long,
    @Schema(description = "키워드", example = "키워드")
    val content: String?
)
