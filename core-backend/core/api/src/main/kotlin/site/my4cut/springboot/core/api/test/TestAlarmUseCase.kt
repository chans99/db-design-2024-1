package site.my4cut.springboot.core.api.test

import site.my4cut.springboot.clients.discord.DiscordAlarmService
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.exception.CoreException

@UseCase
class TestAlarmUseCase(
    private val discordAlarmService: DiscordAlarmService
) {
    fun testDiscordAlarm() {
        discordAlarmService.sendAlarm("테스트 알람")
        discordAlarmService.sendNewMemberAlarm("테스트 회원가입")
        discordAlarmService.sendContributionApplyAlarm(
            title ="기여 신청이 들어왔습니다. 확인해주세요.",
            subTitle = "피드백 정보",
            content = """
                |기여자 : "테스트 기여자"
                |아이디어 : "테스트 아이디어"
                |피드백 타입 : "테스트 피드백 타입"
            """.trimMargin()
        )
        discordAlarmService.sendOrganizationApplyAlarm(
            "단체 신청이 들어왔습니다. 확인해주세요.",
            "단체명 : 테스트 단체계정",
            """
                |단체 설명 : 테스트 설명
                |단체 신청자 : 테스트 신청자
                |단체 신청자 연락처 : 010-1234-5678
            """.trimMargin()
        )
        discordAlarmService.sendMyFrameApplyAlarm(
            "나만의 프레임 신청이 들어왔습니다. 확인해주세요.",
            "나만의 프레임 신청",
            """
                |신청자 : 테스트 신청자
                |신청 내용 : 테스트 내용
                |신청 타입 : 테스트 타입
                |신청자 연락처 : 테스트 연락처
            """
        )
        discordAlarmService.sendErrorAlert(CoreException("테스트 에러 알림"))
    }
}