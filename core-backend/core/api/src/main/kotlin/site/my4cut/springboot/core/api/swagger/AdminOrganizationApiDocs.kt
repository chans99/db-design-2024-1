package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import site.my4cut.springboot.core.api.organization.dto.OrganizationApplyResponse

@Tag(name = "[(Admin) Organization API] 단체 API 어드민 기능 docs")
interface AdminOrganizationApiDocs {

    @Operation(
        summary = "(어드민) 단체 승인",
        description = "단체 신청을 승인하는 API입니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "400", description = "유효하지 않은 요청"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun approve(
        @RequestHeader("Aespa") adminKey: String,
        @PathVariable("organizationId") organizationId: Long
    ): ResponseEntity<Unit>


    @Operation(
        summary = "(어드민) 단체 신청 목록 조회",
        description = "단체 신청한 목록을 조회합니다."
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "400", description = "유효하지 않은 요청"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun getOrganizationApplyList(
        @RequestHeader("Aespa") adminKey: String,
    ): ResponseEntity<List<OrganizationApplyResponse>>
}