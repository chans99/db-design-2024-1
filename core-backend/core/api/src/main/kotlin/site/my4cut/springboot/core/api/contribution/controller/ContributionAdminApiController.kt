package site.my4cut.springboot.core.api.contribution.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import site.my4cut.springboot.core.api.contribution.dto.ContributionResponse
import site.my4cut.springboot.core.api.contribution.usecase.AdminContributionUseCase
import site.my4cut.springboot.core.api.controller.v1.AdminApiV1Controller
import site.my4cut.springboot.core.api.swagger.AdminContributionApiDocs

@AdminApiV1Controller
class ContributionAdminApiController(
    private val adminContributionUseCase: AdminContributionUseCase
) : AdminContributionApiDocs {

    @GetMapping("/contributions")
    override fun getContributions(
        @RequestHeader("Aespa") adminKey: String
    ): ResponseEntity<List<ContributionResponse>> {
        return ResponseEntity.ok(adminContributionUseCase.getContributions(adminKey))
    }

    @PostMapping("/contributions/{contributionId}/approve")
    override fun approve(
        @PathVariable contributionId: Long,
        @RequestHeader("Aespa") adminKey: String
    ) {
        adminContributionUseCase.approve(contributionId, adminKey)
    }
}