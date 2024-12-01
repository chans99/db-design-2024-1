package site.my4cut.springboot.batch.member

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import site.my4cut.springboot.clients.discord.DiscordAlarmService
import site.my4cut.springboot.db.member.MemberJpaRepository
import java.time.LocalDateTime


@Service
class DailyUserInfoSchedulerService(
    private val memberJpaRepository: MemberJpaRepository,
    private val discordAlarmService: DiscordAlarmService
) {

    @Scheduled(cron = "@daily")
    fun execute() {
        val today = LocalDateTime.now()
        val todaySignInCount = memberJpaRepository.countByCreatedDateBetween(today.minusDays(1), today)
        val totalUserCount = memberJpaRepository.count()
        discordAlarmService.sendAlarm("오늘의 가입자 수 $todaySignInCount 명, 총 가입자 수 : $totalUserCount")
    }
}