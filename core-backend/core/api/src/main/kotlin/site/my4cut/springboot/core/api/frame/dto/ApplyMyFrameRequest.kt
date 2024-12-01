package site.my4cut.springboot.core.api.frame.dto

import io.swagger.v3.oas.annotations.media.Schema
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.core.enums.frame.FrameApplyType

@Schema(description = "나만의 네컷 프레임 신청 요청")
data class ApplyMyFrameRequest(
    @Schema(description = "이름", example = "홍길동")
    val name: String,
    @Schema(description = "이메일", example = "")
    val phoneNumber: String,
    @Schema(description = "프레임 신청 타입(SELF_MADE, PRODUCTION_INQUIRY)", example = "SELF_MADE")
    val type: FrameApplyType,
    @Schema(description = "내용", example = "프레임 신청 내용")
    val content: String
) {
    init {
        if (content.length > 500) {
            throw InvalidRequestException("content는 500자 이하로 입력해주세요.")
        }
    }
}
