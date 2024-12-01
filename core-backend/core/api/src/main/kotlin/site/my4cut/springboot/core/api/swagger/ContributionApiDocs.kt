package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import site.my4cut.springboot.core.api.contribution.dto.ContributionRequest
import site.my4cut.springboot.core.api.contribution.dto.ContributionResponse

@Tag(name = "[Contribution API] 기여도 API docs")
interface ContributionApiDocs {

    @Operation(
        summary = "유저 피드백 조회",
        description = "유저 피드백을 조회합니다. App에서는 isApproved가 true인 것만 조회합니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun getContributions(@RequestParam(required = false) isApproved: Boolean? ): ResponseEntity<List<ContributionResponse>>

    @Operation(
        summary = "유저 피드백 등록",
        description = "유저 피드백을 등록을 요청합니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun requestRegistration(@RequestBody request: ContributionRequest) : ResponseEntity<Unit>

}