package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import site.my4cut.springboot.core.api.contribution.dto.ContributionResponse

@Tag(name = "[(Admin) Contribution API] 기여도 Admin API docs")
interface AdminContributionApiDocs {

    @Operation(
        summary = "(어드민) 유저 피드백 조회",
        description = "유저 피드백을 승인여부와 관계없이 조회합니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun getContributions(
        @RequestHeader("Aespa") adminKey: String
    ): ResponseEntity<List<ContributionResponse>>

    @Operation(
        summary = "(어드민) 유저 피드백 승인",
        description = "유저 피드백 심사 요청을 승인합니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun approve(
        @PathVariable contributionId: Long,
        @RequestHeader("Aespa") adminKey: String
    )


}