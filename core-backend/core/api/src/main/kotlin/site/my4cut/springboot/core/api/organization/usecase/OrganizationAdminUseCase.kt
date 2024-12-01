package site.my4cut.springboot.core.api.organization.usecase

import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.core.api.admin.adapter.AdminMemberChecker
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.core.api.member.adapter.MemberFinder
import site.my4cut.springboot.core.api.member.adapter.MemberUpdater
import site.my4cut.springboot.core.api.organization.adapter.OrganizationApplyDeleter
import site.my4cut.springboot.core.api.organization.adapter.OrganizationApplyFinder
import site.my4cut.springboot.core.api.organization.dto.OrganizationApplyResponse

@UseCase
@Transactional(readOnly = true)
class OrganizationAdminUseCase(
    private val organizationApplyFinder: OrganizationApplyFinder,
    private val organizationApplyDeleter: OrganizationApplyDeleter,
    private val adminMemberChecker: AdminMemberChecker,
    private val memberFinder: MemberFinder,
    private val memberUpdater: MemberUpdater
) {

    fun getAppliedOrganizationList(adminKey: String): List<OrganizationApplyResponse> {
        if (!adminMemberChecker.check(adminKey)) {
            throw InvalidRequestException("10300")
        }
        return organizationApplyFinder.findAll()
            .map {
                OrganizationApplyResponse(
                    applicantName = it.applicationName,
                    organizationId = it.id!!,
                    memberId = it.memberId!!,
                    organizationName = it.organizationName,
                    organizationDescription = it.description
                )
            }
    }

    @Transactional
    fun approve(adminKey: String, organizationId: Long) {
        if (!adminMemberChecker.check(adminKey)) {
            throw InvalidRequestException("10300")
        }
        val organizationApplyEntity = organizationApplyFinder.findByIdOrThrow(organizationId)
        val memberEntity = memberFinder.findByIdOrThrow(organizationApplyEntity.memberId!!)
        memberUpdater.turnOffAd(memberEntity)
        memberUpdater.registerOrganization(memberEntity)
        memberUpdater.updateToPremium2Member(memberEntity)
        organizationApplyDeleter.deleteById(organizationId)

    }


}