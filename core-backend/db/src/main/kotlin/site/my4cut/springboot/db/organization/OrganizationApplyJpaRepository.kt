package site.my4cut.springboot.db.organization

import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationApplyJpaRepository: JpaRepository<OrganizationApplyEntity, Long> {
}