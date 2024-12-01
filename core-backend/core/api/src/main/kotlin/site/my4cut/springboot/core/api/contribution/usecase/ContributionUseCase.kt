package site.my4cut.springboot.core.api.contribution.usecase

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.clients.discord.DiscordAlarmService
import site.my4cut.springboot.core.api.contribution.adapter.ContributionFinder
import site.my4cut.springboot.core.api.contribution.adapter.ContributionRegister
import site.my4cut.springboot.core.api.contribution.dto.ContributionRequest
import site.my4cut.springboot.core.api.contribution.dto.ContributionResponse
import site.my4cut.springboot.db.contributor.ContributionEntity


@Component
class ContributionUseCase(
    private val contributionFinder: ContributionFinder,
    private val contributionRegister: ContributionRegister,
    private val discordAlarmService: DiscordAlarmService
) {

    @Transactional
    fun register(request: ContributionRequest) {
        val contribution = ContributionEntity(
            contributorAccount = request.contributorAccount,
            idea = request.idea,
            feedbackType = request.feedbackType,
            isApproved = false
        )
        contributionRegister.register(contribution)
        discordAlarmService.sendContributionApplyAlarm(
            title ="기여 신청이 들어왔습니다. 확인해주세요.",
            subTitle = "피드백 정보",
            content = """
                |기여자 : ${request.contributorAccount}
                |아이디어 : ${request.idea}
                |피드백 타입 : ${request.feedbackType}
            """.trimMargin()
        )
    }

    fun getContributions() : List<ContributionResponse> {
            return contributionFinder.findAll().filter { it.isApproved }.map {
                ContributionResponse(
                    id = it.id!!,
                    contributorAccount = it.contributorAccount,
                    idea = it.idea,
                    createdAt = it.createdAt.toString(),
                )
            }.toList()
    }
}