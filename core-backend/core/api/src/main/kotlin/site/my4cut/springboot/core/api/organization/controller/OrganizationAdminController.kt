package site.my4cut.springboot.core.api.organization.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import site.my4cut.springboot.core.api.controller.v1.AdminApiV1Controller
import site.my4cut.springboot.core.api.organization.dto.OrganizationApplyResponse
import site.my4cut.springboot.core.api.organization.usecase.OrganizationAdminUseCase
import site.my4cut.springboot.core.api.swagger.AdminOrganizationApiDocs

@AdminApiV1Controller
class OrganizationAdminController(
    private val organizationAdminUseCase: OrganizationAdminUseCase
) : AdminOrganizationApiDocs {

    @PostMapping("/organizations/{organizationId}/approve")
    override fun approve(
        @RequestHeader("Aespa") adminKey: String,
        @PathVariable("organizationId") organizationId: Long
    ): ResponseEntity<Unit> {
        organizationAdminUseCase.approve(adminKey, organizationId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/organizations/apply")
    override fun getOrganizationApplyList(
        @RequestHeader("Aespa") adminKey: String,
    ): ResponseEntity<List<OrganizationApplyResponse>> {
        val response = organizationAdminUseCase.getAppliedOrganizationList(adminKey)
        return ResponseEntity.ok(response)
    }

}