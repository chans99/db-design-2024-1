package site.my4cut.springboot.core.api.auth.usecase

import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.core.api.auth.dto.TokenResponse
import site.my4cut.springboot.core.api.config.auth.TokenGenerator
import site.my4cut.springboot.core.api.config.auth.TokenValidator
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.exception.InvalidTokenException
import site.my4cut.springboot.core.api.member.adapter.MemberFinder
import site.my4cut.springboot.core.api.member.adapter.MemberUpdater
import site.my4cut.springboot.core.enums.token.TokenType

@UseCase
@Transactional(readOnly = true)
class TokenUseCase(
    private val tokenGenerator: TokenGenerator,
    private val tokenValidator: TokenValidator,
    private val memberFinder: MemberFinder,
    private val memberUpdater: MemberUpdater
) {
    companion object {
        private const val ACCESS_TOKEN_EXPIRE_HOURS = 2L
        private const val REFRESH_TOKEN_EXPIRE_HOURS = 24 * 14L
        private const val ACCESS_TOKEN_EXPIRE_MINUTES = 5L
        private const val REFRESH_TOKEN_EXPIRE_MINUTES = 10 * 100 * 100L
    }

    @Transactional
    fun reissue(parsedRefreshToken: String): TokenResponse {
        val validatedMemberId = tokenValidator.validate(parsedRefreshToken, TokenType.REFRESH)
        val memberEntity =  memberFinder.findByIdOrThrow(validatedMemberId)

        if (memberEntity.refreshToken != parsedRefreshToken) {
            throw InvalidTokenException("Invalid Refresh Token")
        }
        val newAccessToken = tokenGenerator.generate(validatedMemberId, TokenType.ACCESS, ACCESS_TOKEN_EXPIRE_MINUTES)
        val newRefreshToken = tokenGenerator.generate(validatedMemberId, TokenType.REFRESH, REFRESH_TOKEN_EXPIRE_MINUTES)
        memberUpdater.updateRefreshToken(memberEntity, newRefreshToken)
        return TokenResponse(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }
}