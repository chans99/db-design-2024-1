package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.organization.dto.OrganizationApplyRequest
import site.my4cut.springboot.core.api.organization.dto.OrganizationProfileImageResponse

@Tag(name = "[Organization API] 단체 API docs")
interface OrganizationApiDocs {

    @Operation(summary = "단체 신청 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "단체 신청 성공"),
        ApiResponse(responseCode = "400", description = "단체 신청 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun applyUpgrade(
        @RequestBody request: OrganizationApplyRequest,
        @Auth memberId: Long
    ) : ResponseEntity<Unit>

    @Operation(summary = "단체 업그레이드 취소 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "프레임 키워드 목록 조회 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun cancelOrganizationUpgrade(
        @Auth memberId: Long
    ): ResponseEntity<Unit>


    @Operation(summary = "단체 프로필 사진(로고 사진) 등록 API")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "프레임 키워드 목록 조회 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun updateOrganizationProfile(
        @Auth memberId: Long,
        @RequestPart profileImage: MultipartFile
    ) : ResponseEntity<OrganizationProfileImageResponse>
}