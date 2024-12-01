package site.my4cut.springboot.core.api.contribution.dto

import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "기여 응답")
data class ContributionResponse(
    @Schema(description = "기여 id", example = "1")
    val id: Long,
    @Schema(description = "기여자 계정", example = "my4cut")
    val contributorAccount: String,
    @Schema(description = "기여자 이름", example = "마이네컷")
    val idea: String,
    @Schema(description = "생성 시각")
    val createdAt: String,
) {
}