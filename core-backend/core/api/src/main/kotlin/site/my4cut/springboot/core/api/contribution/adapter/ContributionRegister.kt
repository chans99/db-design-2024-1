package site.my4cut.springboot.core.api.contribution.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.contributor.ContributionEntity
import site.my4cut.springboot.db.contributor.ContributionJpaRepository


@Component
class ContributionRegister(
    private val contributionJpaRepository: ContributionJpaRepository
) {

    fun register(contributionEntity: ContributionEntity) : ContributionEntity {
        return contributionJpaRepository.save(contributionEntity)
    }
}