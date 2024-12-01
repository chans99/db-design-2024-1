package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.frame.dto.FrameInfoResponse
import site.my4cut.springboot.core.api.frame.dto.FrameResponse


@Tag(name = "[Frame API] 프레임 API docs")
interface FrameApiDocs {

    @Operation(summary = "프레임 상세 정보 조회")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "프레임 상세 정보 조회 성공"),
        ApiResponse(responseCode = "404", description = "프레임 정보 없음"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    @GetMapping("/frames/{frameId}")
    fun getFrameInfo(
        @Auth memberId: Long,
        @PathVariable frameId: Long
    ): ResponseEntity<FrameInfoResponse>

    @Operation(summary = "프레임 목록 조회")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "프레임 목록 조회 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun getFrameList(
        @Auth memberId: Long,
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) keywordId: Long?,
    ): ResponseEntity<List<FrameResponse>>


    @Operation(summary = "프레임 다운로드")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "프레임 다운로드 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun downloadFrame(
        @Auth memberId: Long,
        @PathVariable frameId: Long
    ): ResponseEntity<Unit>

}