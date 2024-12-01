package site.my4cut.springboot.core.api.member.usecase

import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.core.api.member.adapter.MemberFinder
import site.my4cut.springboot.core.api.member.adapter.MemberRemover
import site.my4cut.springboot.core.api.member.adapter.MemberUpdater
import site.my4cut.springboot.core.api.member.dto.*
import site.my4cut.springboot.core.api.premiumcode.adapter.PremiumCodeValidator

@UseCase
@Transactional(readOnly = true)
class MemberUseCase(
    private val memberFinder: MemberFinder,
    private val memberRemover: MemberRemover,
    private val memberUpdater: MemberUpdater,
    private val premiumCodeValidator: PremiumCodeValidator
) {

    @Transactional
    fun updateNickname(memberId: Long, request: NicknameUpdateRequest) {
        val member = memberFinder.findByIdOrThrow(memberId)
        memberUpdater.updateNickname(member, request.nickname)
    }

    @Transactional
    fun withdraw(memberId: Long) {
        val member = memberFinder.findByIdOrThrow(memberId)
        memberRemover.remove(member)
    }

    fun getMemberInfo(memberId: Long): MemberInfoResponse {
        val member = memberFinder.findByIdOrThrow(memberId)
        return MemberInfoResponse(
            nickname = member.nickname,
            email = member.email,
            socialType = member.socialType!!,
            isOrganizationRegistered = member.isOrganizationRegistered,
            isAdApplied = member.isAdApplied,
            organizationName = member.organizationName,
            logoImageUrl = member.logoImageUrl,
            membershipLevel = member.membership,
            isDefaultFrameActivated = member.isDefaultFrameActivated
        )
    }

    @Transactional
    fun updateAd(memberId: Long): MemberAdUpdateResponse {
        val member = memberFinder.findByIdOrThrow(memberId)
        return MemberAdUpdateResponse(
            memberUpdater.updateAd(member)
        )
    }

    @Transactional
    fun updateMembershipToPremium(memberId: Long, request: MemberPremiumCodeRequest) {
        if (!premiumCodeValidator.validate(request.premiumCode)) {
            throw InvalidRequestException("유효하지 않은 프리미엄 코드입니다.")
        }
        val member = memberFinder.findByIdOrThrow(memberId)
        memberUpdater.updateToPremium2Member(member)
        memberUpdater.turnOffAd(member)
    }
    @Transactional
    fun updateMembershipToNormal(memberId: Long) {
        val member = memberFinder.findByIdOrThrow(memberId)
        memberUpdater.updateToNormalMember(member)
    }

    @Transactional
    fun updateDefaultFrameActivation(memberId: Long) {
        val member = memberFinder.findByIdOrThrow(memberId)
        memberUpdater.updateDefaultFrameActivation(member)
    }
}