package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.member.dto.MemberAdUpdateResponse
import site.my4cut.springboot.core.api.member.dto.MemberInfoResponse
import site.my4cut.springboot.core.api.member.dto.MemberPremiumCodeRequest
import site.my4cut.springboot.core.api.member.dto.NicknameUpdateRequest


@Tag(name = "[Member API] 회원 API docs")
interface MemberApiDocs {

    @Operation(summary = "회원 정보 조회", description = "회원의 정보를 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun getMemberInfo(memberId: Long) : ResponseEntity<MemberInfoResponse>


    @Operation(summary = "회원 탈퇴", description = "회원을 탈퇴합니다.(Hard Delete)")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun withdraw(memberId: Long) : ResponseEntity<Unit>

    @Operation(summary = "사용자 닉네임 정보 업데이트", description = "사용자 닉네임 정보를 업데이트 합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun updateNickname(
        @RequestBody request: NicknameUpdateRequest,
        @Auth memberId: Long
    ) : ResponseEntity<Unit>

    @Operation(summary = "회원 광고 적용여부 수정(스위치)", description = "회원의 정보를 수정합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun updateAd(
        @Auth memberId: Long
    ) : ResponseEntity<Unit>


    @Operation(summary = "회원 프리미엄 적용", description = "프리미엄 코드를 전송하여 프리미엄 적용 요청.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "400", description = "유효하지 않은 프리미엄 코드"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun changeMembershipToPremium(
        @Auth memberId: Long,
        @RequestBody request: MemberPremiumCodeRequest
    ) : ResponseEntity<Unit>

    @Operation(summary = "회원 프리미엄 해제", description = "프리미엄 적용 취소.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun changeMembershipToNormal(
        @Auth memberId: Long
    ) : ResponseEntity<Unit>

    @Operation(summary = "회원 기본 프레임 활성화 수정(스위치)", description = "회원의 기본 프레임 활성화 여부를 수정합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun updateDefaultFrameActivation(
        @Auth memberId: Long,
    ) : ResponseEntity<Unit>


}
