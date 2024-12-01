package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import site.my4cut.springboot.core.api.auth.dto.SocialLoginRequest
import site.my4cut.springboot.core.api.auth.dto.TokenResponse
import site.my4cut.springboot.core.enums.member.SocialType


@Tag(name = "[Auth API] 인증 API docs")
interface AuthApiDocs {

    @Operation(summary = "소셜 로그인",
        description = "소셜 로그인을 요청합니다. provider에 platform 입력. APPLE, KAKAO, GOOGLE")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun socialLogin(
        @RequestBody request: SocialLoginRequest
    ) : ResponseEntity<TokenResponse>

    @Operation(
        summary = "토큰 재발급",
        description = "토큰 재발급을 요청합니다. refresh token을 사용하여 재발급"
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "성공"),
        ApiResponse(responseCode = "401", description = "인증 실패"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun reissueToken(
        @RequestHeader("Aespa") refreshToken: String
    ) : ResponseEntity<TokenResponse>
}