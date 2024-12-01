package site.my4cut.springboot.clients.oauth

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import site.my4cut.springboot.clients.exception.ClientException
import site.my4cut.springboot.clients.exception.OauthRequestException

@Component
class GoogleLoginService(
) {

    val restClient: RestClient = RestClient.create()

    companion object {
        private const val GOOGLE_AUTH_URL = "https://www.googleapis.com/oauth2/v1/userinfo"
    }

    fun getInfo(token: String) : SocialUserInfo {

        val responseBody = restClient.get()
            .uri(GOOGLE_AUTH_URL)
            .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
            .header("Authorization", "Bearer $token")
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) {
                    _, _ ->   throw OauthRequestException("유효하지 않은 로그인 요청")
            }
            .onStatus(HttpStatusCode::is5xxServerError) {
                    _, _ ->   throw ClientException("Google 서버 오류")
            }
            .body(GoogleUserInfoResponse::class.java)

        if (responseBody == null) {
            throw OauthRequestException("Failed to get google user info")
        }

        return SocialUserInfo(
            socialId = responseBody.id,
            email = responseBody.email,
        )
    }

    data class GoogleUserInfoResponse(
        val id: String,
        val email: String,
        @JsonProperty("verified_email")
        val verifiedEmail: Boolean?,
        val name: String?,
        @JsonProperty("given_name")
        val givenName: String?,
        @JsonProperty("family_name")
        val familyName: String?,
        val picture: String?
    )
}