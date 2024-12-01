package site.my4cut.springboot.core.api.frame.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "FrameStore에 등록되는 Frame 정보 응답")
data class FrameInfoResponse(
    @Schema(description = "프레임 ID", example = "1")
    val id: Long,
    @Schema(description = "프레임 제목", example = "마이네컷 프레임")
    val title: String?,
    @Schema(description = "프레임 만든 사람 닉네임", example = "my4cut")
    val makerName: String?,
    @Schema(description = "프레임 설명", example = "마이네컷 프레임 설명")
    val description: String?,
    @Schema(description = "프레임 이미지 url", example = "https://my4cut.site/frame/1")
    val imageUrl: String,
    @Schema(description = "프레임 썸네일 이미지 url", example = "https://my4cut.site/frame/1/thumbnail")
    val thumbnailImageUrl: String?,
    @Schema(description = "프레임 다운로드수", example = "100")
    var downloadCount: Long,
    @Schema(description = "프레임 조회수", example = "100")
    var viewCount: Long,
    @Schema(description = "프레임 키워드 목록")
    val keywords: List<FrameKeywordResponse>,
    @Schema(description = "사용자가 소유한 프레임인지 여부", example = "true")
    val isMemberOwned: Boolean
)
