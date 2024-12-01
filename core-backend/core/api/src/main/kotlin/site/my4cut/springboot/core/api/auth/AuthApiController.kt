package site.my4cut.springboot.core.api.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import site.my4cut.springboot.core.api.auth.dto.SocialLoginRequest
import site.my4cut.springboot.core.api.auth.dto.TokenResponse
import site.my4cut.springboot.core.api.auth.usecase.SocialLoginUseCase
import site.my4cut.springboot.core.api.auth.usecase.TokenUseCase
import site.my4cut.springboot.core.api.controller.v1.ApiV1Controller
import site.my4cut.springboot.core.api.swagger.AuthApiDocs

@ApiV1Controller
class AuthApiController(
    private val socialLoginUseCase: SocialLoginUseCase,
    private val tokenUseCase: TokenUseCase
) : AuthApiDocs {

    @PostMapping("/auth/login/social")
    override fun socialLogin(
        @RequestBody request: SocialLoginRequest,
    ) : ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(socialLoginUseCase.login(request))
    }

    @PostMapping("/auth/token")
    override fun reissueToken(
        @RequestHeader("Aespa") refreshToken: String
    ) : ResponseEntity<TokenResponse> {
        val parsedToken = refreshToken.split(" ")[1]
        return ResponseEntity.ok(tokenUseCase.reissue(parsedToken))
    }

}