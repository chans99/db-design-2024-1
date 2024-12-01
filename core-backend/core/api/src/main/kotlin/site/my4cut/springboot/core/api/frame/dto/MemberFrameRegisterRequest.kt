package site.my4cut.springboot.core.api.frame.dto

import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "회원 프레임 등록 요청")
data class MemberFrameRegisterRequest(
    @Schema(description = "프레임 제목", example = "마이네컷 프레임")
    val title: String,
    @Schema(description = "프레임 만든 사람 닉네임", example = "my4cut")
    val makerName: String,
    @Schema(description = "프레임 설명", example = "마이네컷 프레임입니다.")
    val description: String,
    @Schema(description = "프레임 이미지 URL", example = "https://my4cut.site/frame/1")
    val imageUrl: String,
    @Schema(description = "프레임 섬네일 이미지 URL", example = "https://my4cut.site/frame/1/thumbnail")
    val thumbnailImageUrl: String,
    @Schema(description = "키워드 목록")
    val keywords: List<KeywordRequest>
) {
}