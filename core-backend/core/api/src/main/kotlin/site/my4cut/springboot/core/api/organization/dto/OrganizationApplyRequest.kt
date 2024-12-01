package site.my4cut.springboot.core.api.organization.dto

import io.swagger.v3.oas.annotations.media.Schema
import site.my4cut.springboot.core.api.exception.InvalidRequestException


@Schema(description = "단체 업그레이드(베타) 신청 요청")
data class OrganizationApplyRequest(
    @Schema(description = "지원자 이름", example = "유난초이")
    val applicantName: String,
    @Schema(description = "단체명", example = "단체명")
    val organizationName: String,
    @Schema(description = "내용(최대 500자)", example = "내용")
    val description: String,
    @Schema(description = "전화번호", example = "010-1234-5678")
    val phone: String,
) {
    init {
        if (description.length > 500) throw InvalidRequestException("description length must be less than 500")
    }
}
