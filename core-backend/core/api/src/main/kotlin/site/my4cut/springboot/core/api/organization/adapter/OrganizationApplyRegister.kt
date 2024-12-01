package site.my4cut.springboot.core.api.organization.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.config.stereotype.Adapter
import site.my4cut.springboot.db.organization.OrganizationApplyEntity
import site.my4cut.springboot.db.organization.OrganizationApplyJpaRepository

@Adapter
class OrganizationApplyRegister(
    private val organizationApplyJpaRepository: OrganizationApplyJpaRepository
) {

    fun register(organizationApplyEntity: OrganizationApplyEntity): OrganizationApplyEntity {
        return organizationApplyJpaRepository.save(organizationApplyEntity)
    }
}