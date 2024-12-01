package site.my4cut.springboot.core.api.member.dto

import io.swagger.v3.oas.annotations.media.Schema
import site.my4cut.springboot.core.api.exception.InvalidRequestException


@Schema(name = "닉네임 수정 요청")
data class NicknameUpdateRequest(
    @Schema(description = "닉네임", example = "마이네컷")
    val nickname: String
) {
    init {
        if (nickname.length !in 2 .. 10) {
            throw InvalidRequestException("닉네임은 2자 이상 10자 이하로 입력해주세요.")
        }
    }
}
