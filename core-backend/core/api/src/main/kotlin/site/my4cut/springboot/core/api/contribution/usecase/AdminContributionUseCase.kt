package site.my4cut.springboot.core.api.contribution.usecase

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.core.api.admin.adapter.AdminMemberChecker
import site.my4cut.springboot.core.api.contribution.adapter.ContributionFinder
import site.my4cut.springboot.core.api.contribution.dto.ContributionResponse
import site.my4cut.springboot.core.api.exception.InvalidRequestException

@Component
@Transactional(readOnly = true)
class AdminContributionUseCase(
    private val contributionFinder: ContributionFinder,
    private val adminMemberChecker: AdminMemberChecker
) {

    fun getContributions(adminKey: String): List<ContributionResponse> {
        if (!adminMemberChecker.check(adminKey)) {
            throw InvalidRequestException("10300")
        }
        return contributionFinder.findAll().map {
            ContributionResponse(
                id = it.id!!,
                contributorAccount = it.contributorAccount,
                idea = it.idea,
                createdAt = it.createdAt.toString(),
            )
        }.toList()
    }

    @Transactional
    fun approve(contributionId: Long, adminKey: String) {
        contributionFinder.findByIdOrThrow(contributionId)
            .apply {
                isApproved = true
            }
    }
}