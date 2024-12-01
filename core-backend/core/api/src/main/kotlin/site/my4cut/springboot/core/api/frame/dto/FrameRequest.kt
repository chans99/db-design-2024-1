package site.my4cut.springboot.core.api.frame.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "프레임 등록 요청")
data class FrameRequest(
    @Schema(description = "프레임 제목")
    val title: String,
    @Schema(description = "프레임 설명")
    val description: String,
    @Schema(description = "프레임 이미지 URL")
    val imageUrl: String,
    @Schema(description = "프레임 썸네일 URL")
    val keywords: List<KeywordRequest>
)
