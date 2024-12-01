package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.frame.dto.FrameRequest


@Tag(name = "[(Admin) Frame API] 프레임 Admin API docs")
interface AdminFrameApiDocs {

    @Operation(summary = "프레임 삭제")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "프레임 삭제 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun removeFrame(
        @PathVariable frameId: Long,
        @RequestHeader("Aespa") adminKey: String
    ) : ResponseEntity<Unit>


    @Operation(summary = "프레임 등록")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "프레임 등록 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun registerFrame(
        @RequestHeader("Aespa") adminKey: String,
        @RequestPart frameImage: MultipartFile,
        @RequestPart frameThumbnail: MultipartFile,
        request: FrameRequest
    ) : ResponseEntity<Unit>
}