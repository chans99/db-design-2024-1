package site.my4cut.springboot.core.api.organization.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.controller.v1.ApiV1Controller
import site.my4cut.springboot.core.api.organization.dto.OrganizationApplyRequest
import site.my4cut.springboot.core.api.organization.dto.OrganizationProfileImageResponse
import site.my4cut.springboot.core.api.organization.usecase.OrganizationUseCase
import site.my4cut.springboot.core.api.swagger.OrganizationApiDocs

@ApiV1Controller
class OrganizationApiController(
    private val organizationUseCase: OrganizationUseCase
) : OrganizationApiDocs {

    @PostMapping("/organizations/apply")
    override fun applyUpgrade(
        @RequestBody request: OrganizationApplyRequest,
        @Auth memberId: Long
    ): ResponseEntity<Unit> {
        organizationUseCase.apply(memberId, request)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/organizations/cancel")
    override fun cancelOrganizationUpgrade(
        @Auth memberId: Long
    ): ResponseEntity<Unit> {
        organizationUseCase.cancel(memberId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/organization/profile")
    override fun updateOrganizationProfile(
        @Auth memberId: Long,
        @RequestPart(value = "profileImage") profileImage: MultipartFile
    ) : ResponseEntity<OrganizationProfileImageResponse> {
        val responseBody = organizationUseCase.updateProfile(memberId, profileImage)
        return ResponseEntity.ok(responseBody)
    }
}
