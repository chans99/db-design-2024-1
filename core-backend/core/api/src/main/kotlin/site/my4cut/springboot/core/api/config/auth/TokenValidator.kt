package site.my4cut.springboot.core.api.config.auth

import com.auth0.jwt.JWT.require
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.InvalidTokenException
import site.my4cut.springboot.core.enums.token.TokenType

@Component
class TokenValidator(
    @Value("\${jwt.secret-key}")
   private val secretKey: String
) {

    private val algorithm = Algorithm.HMAC256(secretKey)

    companion object {
        private const val MEMBER_ID = "memberId"
        private const val TOKEN_TYPE = "tokenType"
        private const val TOKEN_ISSUER = "my4cut"
        private const val TOKEN_PREFIX = "Karina "
    }

    fun validate(token: String, tokenType: TokenType): Long {
        try {
            val verifier: JWTVerifier = require(algorithm)
                .withIssuer(TOKEN_ISSUER)
                .withClaimPresence(MEMBER_ID)
                .withClaimPresence(TOKEN_TYPE)
                .build()
            val decodedJWT = verifier.verify(token)

            if (decodedJWT.getClaim(TOKEN_TYPE).asString() != tokenType.name) {
                throw InvalidTokenException("Invalid token")
            }

            return decodedJWT.getClaim(MEMBER_ID).asLong()
        } catch (e: JWTVerificationException) {
            throw InvalidTokenException("Invalid token")
        }
    }
}