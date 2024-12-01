package site.my4cut.springboot.db.contributor

import org.springframework.data.jpa.repository.JpaRepository

interface ContributionJpaRepository : JpaRepository<ContributionEntity, Long> {
    fun findTop100ByOrderByCreatedAtDesc(): List<ContributionEntity>

    fun findAllByOrderByCreatedAtDesc(): List<ContributionEntity>
}