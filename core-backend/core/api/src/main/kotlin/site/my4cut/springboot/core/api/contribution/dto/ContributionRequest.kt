package site.my4cut.springboot.core.api.contribution.dto

import io.swagger.v3.oas.annotations.media.Schema
import site.my4cut.springboot.core.enums.contribution.ContributionFeedbackType


@Schema(name = "기여 요청")
data class ContributionRequest(
    @Schema(description = "기여자 계정", example = "my4cut")
    val contributorAccount: String,
    @Schema(description = "기여자 이름", example = "마이네컷")
    val idea: String,
    @Schema(description = "기여자 피드백 타입 버그제보, 아이디어제보", example = "BUG or IDEA")
    val feedbackType: ContributionFeedbackType
) {
}