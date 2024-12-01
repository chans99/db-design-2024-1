package site.my4cut.springboot.core.api.contribution.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.CoreException
import site.my4cut.springboot.db.contributor.ContributionEntity
import site.my4cut.springboot.db.contributor.ContributionJpaRepository

@Component
class ContributionFinder(
    private val contributionJpaRepository: ContributionJpaRepository
) {
    fun findAll() : List<ContributionEntity> {
        return contributionJpaRepository.findAllByOrderByCreatedAtDesc()
    }

    fun findByIdOrThrow(id: Long) : ContributionEntity {
        return contributionJpaRepository.findById(id)
        .orElseThrow { CoreException("존재하진 않는 피드백입니다. ") }
    }
}