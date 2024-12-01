package site.my4cut.springboot.core.api.frame.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "회원 소유 프레임 응답")
data class MemberOwnedFrameResponse(
    @Schema(description = "사용자 프레임 id", example = "1")
    val memberPhotoFrameId : Long,
    @Schema(description = "프레임 id", example = "1")
    val frameId: Long,
    @Schema(description = "프레임 만든사람 닉네임", example = "my4cut")
    val makerName: String?,
    @Schema(description = "프레임 이미지 url", example = "https://my4cut.site/frame/1.png")
    val imageUrl: String?,
    @Schema(description = "프레임 섬네일 url", example = "https://my4cut.site/frame/1-thumb.png")
    val thumbnailImageUrl: String?,
    @Schema(description = "프레임 공개 여부", example = "true")
    val isPublic: Boolean,
    @Schema(description = "프레임 순서", example = "true")
    val frameOrder: Long?,
    @Schema(description = "프레임 키워드")
    val keywords: List<FrameKeywordResponse>
)
