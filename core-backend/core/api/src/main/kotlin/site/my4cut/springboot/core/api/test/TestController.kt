package site.my4cut.springboot.core.api.test

import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import site.my4cut.springboot.core.api.exception.CoreException
import site.my4cut.springboot.core.api.exception.InvalidRequestException

@RestController
class TestController(
    private val environment: Environment,
    private val testAlarmUseCase: TestAlarmUseCase
) {

    @PostMapping("/test/discord-alarm")
    fun testDiscordAlarm() : ResponseEntity<Unit> {
        if ("prod" in environment.activeProfiles) {
            throw InvalidRequestException("테스트 환경에서만 사용 가능합니다.")
        }
        testAlarmUseCase.testDiscordAlarm()
        return ResponseEntity.ok().build()
    }

    @PostMapping("/test/sentry")
    fun testSentry(){
        if ("prod" in environment.activeProfiles) {
            throw InvalidRequestException("테스트 환경에서만 사용 가능합니다.")
        }
        throw CoreException("테스트용 Sentry Exception")
    }
}