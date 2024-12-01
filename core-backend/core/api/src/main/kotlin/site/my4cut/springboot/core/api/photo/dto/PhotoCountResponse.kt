package site.my4cut.springboot.core.api.photo.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "사진 카운트 응답")
data class PhotoCountResponse(
    @Schema(description = "전체 촬영 사진 수", example = "100")
    val currentAllPhotoCount: Long
) {
}