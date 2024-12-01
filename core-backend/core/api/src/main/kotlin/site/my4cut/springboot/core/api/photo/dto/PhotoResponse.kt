package site.my4cut.springboot.core.api.photo.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "사진 응답")
data class PhotoResponse(
    @Schema(description = "사진 id", example = "1")
    val photoId: Long,
    @Schema(description = "사진 url", example = "https://my4cut.site/photo/1")
    val imageUrl: String
) {
}