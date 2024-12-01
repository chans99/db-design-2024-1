package site.my4cut.springboot.core.api.photo.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "사진 저장 응답")
data class PhotoSaveResponse(
    @Schema(description = "사진 코드", example = "1")
    val photoCode: String?,
) {
}