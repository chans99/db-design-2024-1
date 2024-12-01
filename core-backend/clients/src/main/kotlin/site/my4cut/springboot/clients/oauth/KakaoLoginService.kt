package site.my4cut.springboot.clients.oauth

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import site.my4cut.springboot.clients.exception.ClientException
import site.my4cut.springboot.clients.exception.OauthRequestException

@Component
class KakaoLoginService {
    val restClient: RestClient = RestClient.create()

    companion object {
        private const val KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"
    }

    fun getInfo(token: String) : SocialUserInfo {
        val responseBody = restClient.post()
            .uri(KAKAO_USER_INFO_URL)
            .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
            .header("Authorization", "Bearer $token")
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) {
                    _, _ ->   throw OauthRequestException("유효하지 않은 로그인 요청")
            }
            .onStatus(HttpStatusCode::is5xxServerError) {
                    _, _ ->   throw ClientException("Kakao 서버 오류")
            }
            .body(KakaoUserInfoResponse::class.java)

        if (responseBody == null) {
            throw ClientException("Failed to get user info")
        }

        return SocialUserInfo(
            socialId = responseBody.id.toString(),
            email = responseBody.kakaoAccount.email ?: ""
        )

    }

//    private fun authenticate(code: String) : String {
//        try {
//            val responseBody = restClient.post()
//                .uri("$KAKAO_AUTH_URL?grant_type=$GRANT_TYPE&client_id=$clientId&redirect_uri=$redirectUrl&code=$code")
//                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
//                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError) {
//                        _, _ ->   throw OauthRequestException("유효하지 않은 로그인 요청")
//                }
//                .onStatus(HttpStatusCode::is5xxServerError) {
//                        _, _ ->   throw ClientException("Kakao 서버 오류")
//                }
//                .body(KakaoAuthResponse::class.java)
//            if (responseBody != null) {
//                return responseBody.accessToken
//            } else {
//                throw ClientException("Failed to authenticate")
//            }
//        } catch (e: Exception) {
//            throw ClientException("Failed to authenticate")
//        }
//
//    }
}

data class KakaoUserInfoResponse(
    val id: Long,
    @JsonProperty("connected_at")
    val connectedAt: String,
    @JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccountResponse
)

data class KakaoAccountResponse(
    @JsonProperty("has_email")
    val hasEmail: Boolean,
    @JsonProperty("email_needs_agreement")
    val emailNeedsAgreement: Boolean,
    @JsonProperty("is_email_valid")
    val isEmailValid: Boolean,
    @JsonProperty("is_email_verified")
    val isEmailVerified: Boolean,
    val email: String?
)