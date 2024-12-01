package site.my4cut.springboot.core.api.frame.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "회원 프레임 요청")
data class MemberFrameRequest(
    @Schema(description = "사용자 프레임 id", example = "1")
    val memberFrameId: Long,
    @Schema(description = "프레임 순서", example = "3")
    val frameOrder: Long
)
