package site.my4cut.springboot.core.api.member.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.CoreException
import site.my4cut.springboot.core.enums.member.MembershipLevel
import site.my4cut.springboot.db.member.MemberEntity
import site.my4cut.springboot.db.member.MemberJpaRepository

@Component
class MemberUpdater(
    private val memberJpaRepository: MemberJpaRepository
) {

    fun updateNickname(member: MemberEntity, nickname: String) {
        memberJpaRepository.findById(member.id!!)
            .orElseThrow { CoreException("존재하지 않는 사용자입니다. id : ${member.id}") }
            .apply {
                this.nickname = nickname
            }
    }

    fun updateMemberInfo(member: MemberEntity) {
        memberJpaRepository.findById(member.id!!)
            .orElseThrow { CoreException("존재하지 않는 사용자입니다. id : ${member.id}") }
            .apply {
                this.nickname = member.nickname
                this.email = member.email
                this.socialType = member.socialType
            }
    }

    fun updateAd(member: MemberEntity) : Boolean {
        member.isAdApplied = !member.isAdApplied
        return member.isAdApplied
    }

    fun turnOffAd(member: MemberEntity) {
        member.isAdApplied = false
    }

    fun registerOrganization(member: MemberEntity) {
        member.isOrganizationRegistered = true
    }

    fun updateToNormalMember(member: MemberEntity) {
        member.membership = MembershipLevel.NORMAL
    }

    fun cancelOrganization(member: MemberEntity) {
        member.isOrganizationRegistered = false
    }

    fun updateToPremium2Member(member: MemberEntity) {
        member.membership = MembershipLevel.PREMIUM2
    }

    fun updateRefreshToken(member: MemberEntity, refreshToken: String) {
        member.refreshToken = refreshToken
    }

    fun updateDefaultFrameActivation(member: MemberEntity) {
        member.isDefaultFrameActivated = !member.isDefaultFrameActivated
    }

    fun updateLogoImageUrl(member: MemberEntity, logoImageUrl: String) {
        member.logoImageUrl = logoImageUrl
    }

    fun updateSocialInfo(member: MemberEntity, email: String, socialId: String) {
        member.email = email
        member.socialId = socialId
    }

}