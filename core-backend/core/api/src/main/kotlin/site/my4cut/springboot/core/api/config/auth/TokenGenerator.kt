package site.my4cut.springboot.core.api.config.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.CoreException
import site.my4cut.springboot.core.api.exception.InvalidTokenException
import site.my4cut.springboot.core.enums.token.TokenType
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC
import java.util.*

@Component
class TokenGenerator(
    @Value("\${jwt.secret-key}")
    private val secretKey: String
) {
    private val algorithm: Algorithm = Algorithm.HMAC256(secretKey)

    companion object {
        private const val MEMBER_ID = "memberId"
        private const val TOKEN_TYPE = "tokenType"
        private const val TOKEN_ISSUER = "my4cut"
    }
    fun generate(memberId: Long, tokenType: TokenType, hours: Long): String {
        try {
            val expiresAt = Date.from(LocalDateTime.now().plusMinutes(hours).toInstant(UTC))
            return JWT.create()
                .withIssuer(TOKEN_ISSUER)
                .withClaim(MEMBER_ID, memberId)
                .withClaim(TOKEN_TYPE, tokenType.name)
                .withExpiresAt(expiresAt)
                .sign(algorithm)
        } catch (e: CoreException) {
            throw InvalidTokenException("Token 생성에 실패했습니다.")
        }
    }
}