package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.frame.dto.MemberFrameRegisterRequest
import site.my4cut.springboot.core.api.frame.dto.MemberFrameRequest
import site.my4cut.springboot.core.api.frame.dto.MemberOwnedFrameResponse

@Tag(name = "[MemberFrame API] 사용자 프레임 API docs")
interface MemberFrameApiDocs {

    @Operation(
        summary = "사용자 프레임 조회",
        description = "사용자의 프레임을 조회합니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun getMemberFrames(
        @Auth memberId: Long,
        keywordId: Long?
    ) : ResponseEntity<List<MemberOwnedFrameResponse>>


    @Operation(
        summary = "사용자 프레임 삭제",
        description = "사용자의 프레임을 삭제합니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun deleteFrame(
        @Auth memberId: Long,
        @PathVariable memberFrameId: Long
    ): ResponseEntity<Unit>


    @Operation(
        summary = "사용자 프레임 순서 변경",
        description = "사용자가 등록한 프레임 순서를 변경합니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun changeFrameOrder(
        @Auth memberId: Long,
        @RequestBody request: List<MemberFrameRequest>
    ): ResponseEntity<Unit>


    @Operation(
        summary = "사용자 프레임 등록",
        description = "사용자의 프레임을 등록합니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun registerMemberFrame(
        @Auth memberId: Long,
        @RequestBody request: MemberFrameRegisterRequest
    ): ResponseEntity<Unit>
}