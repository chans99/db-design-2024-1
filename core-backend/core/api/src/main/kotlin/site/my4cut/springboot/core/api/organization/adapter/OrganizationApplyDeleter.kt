package site.my4cut.springboot.core.api.organization.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.organization.OrganizationApplyJpaRepository

@Component
class OrganizationApplyDeleter(
    private val organizationApplyJpaRepository: OrganizationApplyJpaRepository
) {

    fun deleteById(organizationId: Long) {
        organizationApplyJpaRepository.deleteById(organizationId)
    }
}