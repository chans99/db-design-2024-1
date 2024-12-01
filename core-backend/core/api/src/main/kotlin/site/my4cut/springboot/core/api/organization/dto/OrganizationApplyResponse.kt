package site.my4cut.springboot.core.api.organization.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "단체 가입 응답")
data class OrganizationApplyResponse(
    @Schema(description = "단체 가입 신청자 이름", example = "마이네컷")
    val applicantName: String,
    @Schema(description = "단체 id", example = "1")
    val organizationId: Long,
    @Schema(description = "단체 이름", example = "마이네컷")
    val organizationName: String,
    @Schema(description = "단체 설명", example = "마이네컷은 ...")
    val organizationDescription: String,
    @Schema(description = "단체 가입 사용자 id", example = "1")
    val memberId: Long
) {
}