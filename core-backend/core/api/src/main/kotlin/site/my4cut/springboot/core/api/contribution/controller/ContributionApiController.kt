package site.my4cut.springboot.core.api.contribution.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import site.my4cut.springboot.core.api.contribution.dto.ContributionRequest
import site.my4cut.springboot.core.api.contribution.dto.ContributionResponse
import site.my4cut.springboot.core.api.contribution.usecase.ContributionUseCase
import site.my4cut.springboot.core.api.controller.v1.ApiV1Controller
import site.my4cut.springboot.core.api.swagger.ContributionApiDocs

@ApiV1Controller
class ContributionApiController(
    private val contributionUseCase: ContributionUseCase
) : ContributionApiDocs {
    @GetMapping("/contributions")
    override fun getContributions(@RequestParam(required = false) isApproved: Boolean? ): ResponseEntity<List<ContributionResponse>> {
        return ResponseEntity.ok(contributionUseCase.getContributions())
    }

    @PostMapping("/contributions/registration")
    override fun requestRegistration(@RequestBody request: ContributionRequest) : ResponseEntity<Unit> {
        contributionUseCase.register(request)
        return ResponseEntity.noContent().build()
    }

}