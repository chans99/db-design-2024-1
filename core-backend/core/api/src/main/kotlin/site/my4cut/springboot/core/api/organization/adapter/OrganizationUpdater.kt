package site.my4cut.springboot.core.api.organization.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.member.MemberJpaRepository
import site.my4cut.springboot.db.organization.OrganizationApplyJpaRepository

@Component
class OrganizationUpdater(
    private val memberJpaRepository: MemberJpaRepository,
    private val organizationApplyJpaRepository: OrganizationApplyJpaRepository
) {

    fun update() {
    }
}