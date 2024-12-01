package site.my4cut.springboot.core.api.organization.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "단체 프로필 이미지 응답")
data class OrganizationProfileImageResponse(
    @Schema(description = "단체 프로필 이미지 URL", example = "https://my4cut.site/organization/1/profile")
    val logoImageUrl: String
) {
}