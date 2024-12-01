package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.frame.dto.FrameKeywordResponse

@Tag(name = "[Frame Keyword API] 프레임 키워드 API docs")
interface FrameKeywordApiDocs {

    @Operation(summary = "프레임 키워드 목록 조회")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "프레임 키워드 목록 조회 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun getFrameKeyword(memberId: Long): ResponseEntity<List<FrameKeywordResponse>>

    @Operation(summary = "유저 프레임 키워드 목록 조회", description = "유저가 등록한 프레임의 키워드 목록을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "유저 프레임 키워드 목록 조회 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun getUserFrameKeywordUser(@Auth memberId: Long): ResponseEntity<List<FrameKeywordResponse>>
}