package site.my4cut.springboot.core.api.photo.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "사진 url 응답")
data class PhotoUrlResponse(
    @Schema(description = "사진 url", example = "https://my4cut.site")
    val photoUrl: String?,
    @Schema(description = "비디오 url", example = "https://my4cut.site")
    val videoUrl: String?
)
