package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.frame.dto.ApplyMyFrameRequest

@Tag(name = "[MyFrame API] 나만의 네컷 프레임 API docs")
interface MyFrameApiDocs {

    @Operation(summary = "프레임 상세 정보 조회")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "프레임 상세 정보 조회 성공"),
        ApiResponse(responseCode = "404", description = "프레임 정보 없음"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun applyFrame(
        @RequestBody request: ApplyMyFrameRequest,
        @Auth memberId: Long
    ) : ResponseEntity<Unit>
}