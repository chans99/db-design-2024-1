package site.my4cut.springboot.core.api.auth.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.clients.oauth.AppleLoginService
import site.my4cut.springboot.clients.oauth.GoogleLoginService
import site.my4cut.springboot.clients.oauth.KakaoLoginService
import site.my4cut.springboot.core.api.auth.dto.SocialInfo
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.core.enums.member.SocialType

@Component
class SocialProvider(
    private val kakaoLoginService: KakaoLoginService,
    private val googleLoginService: GoogleLoginService,
    private val appleLoginService: AppleLoginService
) {
    fun getSocialInfo(socialType: SocialType, token: String) : SocialInfo {
        val socialLoginResponse = when(socialType) {
            SocialType.KAKAO -> kakaoLoginService.getInfo(token)
            SocialType.GOOGLE -> googleLoginService.getInfo(token)
            SocialType.APPLE -> appleLoginService.getInfo(token)
            else -> throw InvalidRequestException("Invalid provider")
        }

        return SocialInfo(
            socialId = socialLoginResponse.socialId,
            email = socialLoginResponse.email,
        )

    }
}