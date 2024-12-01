package site.my4cut.springboot.core.api.member.dto

import io.swagger.v3.oas.annotations.media.Schema


@Schema(name = "광고 신청 응답")
data class MemberAdUpdateResponse(
    @Schema(description = "광고 신청 여부", example = "True")
    val isAdApplied: Boolean
)
