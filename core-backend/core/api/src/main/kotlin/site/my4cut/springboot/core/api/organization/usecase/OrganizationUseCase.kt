package site.my4cut.springboot.core.api.organization.usecase

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.clients.discord.DiscordAlarmService
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.member.adapter.MemberFinder
import site.my4cut.springboot.core.api.member.adapter.MemberUpdater
import site.my4cut.springboot.core.api.organization.adapter.OrganizationApplyRegister
import site.my4cut.springboot.core.api.organization.adapter.OrganizationProfileImageUploader
import site.my4cut.springboot.core.api.organization.dto.OrganizationApplyRequest
import site.my4cut.springboot.core.api.organization.dto.OrganizationProfileImageResponse
import site.my4cut.springboot.core.api.photo.adapter.PhotoDeleter
import site.my4cut.springboot.db.organization.OrganizationApplyEntity

@UseCase
@Transactional(readOnly = true)
class OrganizationUseCase(
    private val memberFinder: MemberFinder,
    private val memberUpdater: MemberUpdater,
    private val organizationApplyRegister: OrganizationApplyRegister,
    private val organizationProfileImageUploader: OrganizationProfileImageUploader,
    private val photoDeleter: PhotoDeleter,
    private val discordAlarmService: DiscordAlarmService
) {

    @Transactional
    fun apply(memberId: Long, request: OrganizationApplyRequest) {
        val member = memberFinder.findByIdOrThrow(memberId)
        val organizationApplyEntity = OrganizationApplyEntity(
            memberId = member.id,
            applicationName = request.applicantName,
            organizationName = request.organizationName,
            description = request.description,
            phone = request.phone
        )
        organizationApplyRegister.register(organizationApplyEntity)
        discordAlarmService.sendOrganizationApplyAlarm(
            "단체 신청이 들어왔습니다. 확인해주세요.",
            "단체명 : ${request.organizationName}",
            """
                |단체 설명 : ${request.description}
                |단체 신청자 : ${request.applicantName}
                |단체 신청자 연락처 : ${request.phone}
            """
        )
    }

    @Transactional
    fun cancel(memberId: Long) {
        val member = memberFinder.findByIdOrThrow(memberId)
        memberUpdater.cancelOrganization(member)
    }

    @Transactional
    fun updateProfile(memberId: Long, profileImage: MultipartFile): OrganizationProfileImageResponse {
        val member = memberFinder.findByIdOrThrow(memberId)
        if (member.logoImageUrl != "" && member.logoImageUrl != null) {
            photoDeleter.delete(member.logoImageUrl!!)
        }
        val imageUrl = organizationProfileImageUploader.upload(member.userCode.toString(), profileImage)
        memberUpdater.updateLogoImageUrl(member, imageUrl)
        return OrganizationProfileImageResponse(
            logoImageUrl = imageUrl
        )
    }
}