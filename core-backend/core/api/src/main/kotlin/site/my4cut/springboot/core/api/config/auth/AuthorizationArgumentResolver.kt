package site.my4cut.springboot.core.api.config.auth

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import site.my4cut.springboot.core.api.exception.InvalidTokenException
import site.my4cut.springboot.core.api.member.adapter.MemberFinder
import site.my4cut.springboot.core.enums.token.TokenType

@Component
class AuthorizationArgumentResolver(
    private val tokenValidator: TokenValidator,
    private val memberFinder: MemberFinder,
) : HandlerMethodArgumentResolver {

    companion object {
        private const val AUTHORIZATION_PREFIX = "Aespa"
        private const val TOKEN_PREFIX = "Karina"
    }
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(Auth::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java)
        val jwt = request?.let { extractToken(it) } ?: throw InvalidTokenException("Invalid Token")
        val memberId = tokenValidator.validate(jwt, TokenType.ACCESS)
        if (!memberFinder.existsById(memberId)) {
            throw InvalidTokenException("Invalid Token")
        }
        return memberId
    }

    private fun extractToken(request: HttpServletRequest): String {
        val token = request.getHeader(AUTHORIZATION_PREFIX)
        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            throw InvalidTokenException("Invalid Token")
        }
        return token.substring(7)
    }
}