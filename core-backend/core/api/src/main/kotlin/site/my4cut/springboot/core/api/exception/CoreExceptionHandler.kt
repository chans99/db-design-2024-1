package site.my4cut.springboot.core.api.exception

import io.sentry.Sentry
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MultipartException
import org.springframework.web.servlet.resource.NoResourceFoundException
import site.my4cut.springboot.clients.discord.DiscordAlarmService
import site.my4cut.springboot.clients.exception.ClientException
import site.my4cut.springboot.clients.exception.OauthRequestException
import site.my4cut.springboot.core.api.config.logger.getLogger

@RestControllerAdvice
class CoreExceptionHandler(
    private val discordAlarmService: DiscordAlarmService
) {
    private val logger = getLogger()

    /*
    401 Unauthorized
     */
    @ExceptionHandler(InvalidTokenException::class)
    fun handleInvalidTokenException(e: InvalidTokenException): ResponseEntity<ErrorResponse> {
        Sentry.captureException(e)
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(e.message))
    }

    /*
    400 Bad Request
   */

    @ExceptionHandler(InvalidRequestException::class)
    fun handleInvalidRequestException(e: InvalidRequestException): ResponseEntity<ErrorResponse> {
        Sentry.captureException(e)
        return ResponseEntity
            .badRequest()
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        Sentry.captureException(e)
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse("10404"))
    }

    /*
    403 Forbidden
    */
    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(e: AccessDeniedException): ResponseEntity<ErrorResponse> {
        Sentry.captureException(e)
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN.value())
            .body(ErrorResponse(e.message))
    }


    /*
   500 Internal Server Error
   */
    @ExceptionHandler(MultipartException::class)
    fun handleMultipartException(e: MultipartException): ResponseEntity<ErrorResponse> {
        Sentry.captureException(e)
        discordAlarmService.sendErrorAlert(e)
        return ResponseEntity.internalServerError()
            .body(ErrorResponse("10501"))
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(e: NoResourceFoundException): ResponseEntity<ErrorResponse> {
        Sentry.captureException(e)
        return ResponseEntity.internalServerError()
            .body(ErrorResponse("10000"))
    }


    @ExceptionHandler(CoreException::class)
    fun handleCoreException(e: CoreException): ResponseEntity<ErrorResponse> {
        discordAlarmService.sendErrorAlert(e)
        Sentry.captureException(e)
        return ResponseEntity.internalServerError()
            .body(ErrorResponse("10000"))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        discordAlarmService.sendErrorAlert(e)
        Sentry.captureException(e)
        return ResponseEntity.internalServerError()
            .body(ErrorResponse("10000"))
    }

    /*
    Client Module Exception
     */
    @ExceptionHandler(OauthRequestException::class)
    fun handleOauthRequestException(e: OauthRequestException): ResponseEntity<ErrorResponse> {
        discordAlarmService.sendErrorAlert(e)
        Sentry.captureException(e)
        return ResponseEntity.badRequest()
            .body(ErrorResponse(e.message!!))
    }

    @ExceptionHandler(ClientException::class)
    fun handleClientException(e: ClientException): ResponseEntity<ErrorResponse> {
        discordAlarmService.sendErrorAlert(e)
        Sentry.captureException(e)
        return ResponseEntity.internalServerError()
            .body(ErrorResponse("10500"))
    }
}