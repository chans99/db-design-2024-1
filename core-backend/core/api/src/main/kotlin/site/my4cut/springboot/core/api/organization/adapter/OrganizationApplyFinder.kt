package site.my4cut.springboot.core.api.organization.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.EntityNotFoundException
import site.my4cut.springboot.db.organization.OrganizationApplyEntity
import site.my4cut.springboot.db.organization.OrganizationApplyJpaRepository

@Component
class OrganizationApplyFinder(
    private val organizationApplyJpaRepository: OrganizationApplyJpaRepository
) {

    fun findByIdOrThrow(organizationId: Long) : OrganizationApplyEntity {
        return organizationApplyJpaRepository.findById(organizationId)
            .orElseThrow { throw EntityNotFoundException("존재하지 않는 단체신청입니다.") }
    }

    fun findAll() : List<OrganizationApplyEntity> {
        return organizationApplyJpaRepository.findAll()
    }
}