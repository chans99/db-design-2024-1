package site.my4cut.springboot.core.api.auth.usecase

import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.clients.discord.DiscordAlarmService
import site.my4cut.springboot.core.api.auth.adapter.SocialProvider
import site.my4cut.springboot.core.api.auth.dto.SocialLoginRequest
import site.my4cut.springboot.core.api.auth.dto.TokenResponse
import site.my4cut.springboot.core.api.config.auth.TokenGenerator
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.member.adapter.MemberFinder
import site.my4cut.springboot.core.api.member.adapter.MemberRegister
import site.my4cut.springboot.core.api.member.adapter.MemberUpdater
import site.my4cut.springboot.core.enums.token.TokenType
import site.my4cut.springboot.db.member.MemberEntity

@UseCase
@Transactional(readOnly = true)
class SocialLoginUseCase(
    private val socialProvider: SocialProvider,
    private val memberRegister: MemberRegister,
    private val memberFinder: MemberFinder,
    private val tokenGenerator: TokenGenerator,
    private val discordAlarmService: DiscordAlarmService,
    private val memberUpdater: MemberUpdater
) {
    companion object {
        private const val ACCESS_TOKEN_EXPIRE_HOURS = 2L
        private const val REFRESH_TOKEN_EXPIRE_HOURS = 24 * 14L
        private const val RANDOM_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    }

    @Transactional
    fun login(request: SocialLoginRequest): TokenResponse {
        val socialResponse = socialProvider.getSocialInfo(request.socialType, request.token)
        val email = socialResponse.email
        val socialId = socialResponse.socialId

        // 1.0 유저 유입 확인을 위한 로직
        val isExistMemberBySocialTypeAndEmail = memberFinder.existsBySocialTypeAndEmail(request.socialType, email)
        if (isExistMemberBySocialTypeAndEmail) {
            val member = memberFinder.findBySocialTypeAndEmail(request.socialType, email)!!
            val accessToken = tokenGenerator.generate(member.id!!, TokenType.ACCESS, ACCESS_TOKEN_EXPIRE_HOURS)
            val refreshToken = tokenGenerator.generate(member.id!!, TokenType.REFRESH, REFRESH_TOKEN_EXPIRE_HOURS)
            memberUpdater.updateRefreshToken(member, refreshToken)
            memberUpdater.updateSocialInfo(member, email, socialId)
            return TokenResponse(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }

        val isExistMember = memberFinder.existBySocialId(socialId)
        if (isExistMember) {
            val member = memberFinder.findBySocialId(socialId)!!
            val accessToken = tokenGenerator.generate(member.id!!, TokenType.ACCESS, ACCESS_TOKEN_EXPIRE_HOURS)
            val refreshToken = tokenGenerator.generate(member.id!!, TokenType.REFRESH, REFRESH_TOKEN_EXPIRE_HOURS)
            memberUpdater.updateRefreshToken(member, refreshToken)
            return TokenResponse(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }

        val newMember = registerNewMember(request, socialId, email)
        val accessToken = tokenGenerator.generate(newMember.id!!, TokenType.ACCESS, ACCESS_TOKEN_EXPIRE_HOURS)
        val refreshToken = tokenGenerator.generate(newMember.id!!, TokenType.REFRESH, REFRESH_TOKEN_EXPIRE_HOURS)
        memberUpdater.updateRefreshToken(newMember, refreshToken)
        memberUpdater.updateNickname(newMember, generateRandomString())
        discordAlarmService.sendNewMemberAlarm("새로운 Member $email 회원가입!")
        return TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    private fun generateRandomString(): String {
        return (1..8)
            .map { RANDOM_STRING.random() }
            .joinToString("")
    }

    private fun registerNewMember(request: SocialLoginRequest, socialId: String, email: String): MemberEntity {
        return memberRegister.register(
            MemberEntity(
                socialId = socialId,
                socialType = request.socialType,
                isAdApplied = true,
                email = email
            )
        )
    }
}