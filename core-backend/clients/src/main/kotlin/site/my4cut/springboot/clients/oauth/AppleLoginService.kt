package site.my4cut.springboot.clients.oauth

import com.auth0.jwt.JWT
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import site.my4cut.springboot.clients.exception.ClientException
import site.my4cut.springboot.clients.exception.OauthRequestException
import java.math.BigInteger
import java.security.KeyFactory
import java.security.NoSuchAlgorithmException
import java.security.PublicKey
import java.security.spec.RSAPublicKeySpec
import java.util.*

@Component
class AppleLoginService(
    private val objectMapper: ObjectMapper
) {
    companion object {
        private const val APPLE_PUBLIC_KEY_URL = "https://appleid.apple.com/auth/keys"
    }

    private val client = RestClient.create()

    fun getInfo(token: String): SocialUserInfo {
        val applePublicKey = getApplePublicKey()
        val decodedAppleKey = decodeAppleAccessToken(token)
        val matchedAppleKey = matchDecodedKeyWithApplePublicKeys(decodedAppleKey, applePublicKey!!)
        try {
            val decodedJWT = JWT.decode(token)
            return SocialUserInfo(
                socialId = decodedJWT.getClaim("sub").asString(),
                email = decodedJWT.getClaim("email").asString(),
            )
        } catch (e: Exception) {
            throw OauthRequestException("Failed to get apple user info")
        }
    }

    private fun decodeAppleAccessToken(token: String): DecodedAppleKey {
        try {
            val encoded = token.split(".")
            val decodedHeader = String(Base64.getDecoder().decode(encoded[0]))
            val decodedMap = objectMapper.readValue(decodedHeader, Map::class.java)
            return DecodedAppleKey(
                kid = decodedMap["kid"] as String,
                alg = decodedMap["alg"] as String
            )
        } catch (e: Exception) {
            throw OauthRequestException("Failed to decode apple token")
        }
    }


    private fun getApplePublicKey(): ApplePublicKeyResponse? {
        return client.get()
            .uri(APPLE_PUBLIC_KEY_URL)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { _, _ ->
                throw OauthRequestException("유효하지 않은 로그인 요청")
            }
            .onStatus(HttpStatusCode::is5xxServerError) { _, _ ->
                throw ClientException("Apple 서버 오류")
            }
            .body(ApplePublicKeyResponse::class.java)
    }

    private fun matchDecodedKeyWithApplePublicKeys(
        decodedAppleKey: DecodedAppleKey,
        appleKeyList: ApplePublicKeyResponse
    ): ApplePublicKey {
        return appleKeyList.keys.first {
            it.kid == decodedAppleKey.kid && it.alg == decodedAppleKey.alg
        }
    }

    private fun generatePublicKey(key: ApplePublicKey): PublicKey {
        try {
            val eBytes = Base64.getUrlDecoder().decode(key.e)
            val nBytes = Base64.getUrlDecoder().decode(key.n)
            val publicKeySpec = RSAPublicKeySpec(
                BigInteger(1, nBytes),
                BigInteger(1, eBytes)
            )
            val keyFactory = KeyFactory.getInstance(key.kty)
            return keyFactory.generatePublic(publicKeySpec)
        } catch (e: NoSuchAlgorithmException) {
            throw OauthRequestException("Failed to generate public key")
        }
    }
}

data class ApplePublicKeyResponse(
    val keys: List<ApplePublicKey>
)

data class ApplePublicKey(
    val kty: String,
    val kid: String,
    val use: String,
    val alg: String,
    val n: String,
    val e: String
)

data class AppleKey(
    val keys: List<ApplePublicKey>
)

data class DecodedAppleKey(
    val kid: String,
    val alg: String
)