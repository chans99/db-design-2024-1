package site.my4cut.springboot.clients.discord

import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.servlet.resource.NoResourceFoundException
import java.lang.Exception

@Component
class DiscordAlarmService {

    private val restClient = RestClient.create()

    companion object {
        const val ERROR_ALERT_URL =
            "https://discord.com/api/webhooks/1288903707836022845/1WO52DUyL87tIWTCN-TyxfYMIa-Wjr2tGMKSRGXG9usIbY5ka9wyu7PDp1QMSYzuRP_k"
        const val NEW_MEMBER_ALARM_BOT_URL =
            "https://discord.com/api/webhooks/1289118109055320065/xeyBLq-tVsekWwwxPsblB12LMBoaszNzAr-qzh4WHSxkyWnFMc_NvuFhMgKwUf20YBif"
        const val ORGANIZATION_APPLY_URL =
            "https://discord.com/api/webhooks/1298644480274202624/O5QYS63MUVcZZZwpbNgS0BVYQLphJRKM04o6XKLPBM2pnBZCEguukP1BvN20wFligVpS"
        const val CONTRIBUTION_APPLY_URL =
            "https://discord.com/api/webhooks/1298644961583173682/Knv9rnVuzgGcUxx1wXRpNSN9R2i4g7O7r5dLmWAkzS6Ir4ndsKC0gSd7sEVG__durtEo"
        const val ALARM_URL =
            "https://discord.com/api/webhooks/1298644863918805044/gAOmTg20nkQEXKKWVbj07BkfMiGdGVvi9UoZmYSF07LYxd8iB__C-jiNkQY3Pq9qlhT4"
        const val ADMIN_ALARM_URL = "https://discord.com/api/webhooks/1298692218248364112/nzLqRbDeAuIlmHpSjD6tIjy-rWcki5W0_vNygjAyDn1IseDot9rbnPNT5Ww_6utIy4mK"
        const val MY_FRAME_APPLY_URL = "https://discord.com/api/webhooks/1300113845691486268/ebrHbJrXMicwEkm9WGEEUxSvrC53tTBnskxAr6jKpOP-Cz2X2bqaAxGUuuAIZHwFlh7N"
    }

    fun sendErrorAlert(exception: Exception) {
        if (exception is NoResourceFoundException) {
            val requestBody = DiscordRequest(
                content = "에러 발생 : ${exception.message} ( ${exception.javaClass.simpleName} )",
                embeds = null
            )
            restClient.post()
                .uri(ERROR_ALERT_URL)
                .body(requestBody)
                .retrieve()
            return
        }
        val requestBody = DiscordRequest(
            content = "에러 발생 : ${exception.message} ( ${exception.javaClass.simpleName} )",
            listOf(
                Embed(
                    title = "Stack Trace",
                    description = parseStackTrace(exception)
                )
            )

        )
        restClient.post()
            .uri(ERROR_ALERT_URL)
            .body(requestBody)
            .retrieve()
    }

    fun sendNewMemberAlarm(message: String) {
        val requestBody = DiscordRequest(
            content = message,
            embeds = null
        )
        restClient.post()
            .uri(NEW_MEMBER_ALARM_BOT_URL)
            .body(requestBody)
            .retrieve()
    }

    fun sendAlarm(message: String) {
        val requestBody = DiscordRequest(
            content = message,
            embeds = null
        )
        restClient.post()
            .uri(ALARM_URL)
            .body(requestBody)
            .retrieve()
    }

    fun sendContributionApplyAlarm(title: String, subTitle: String, content: String) {
        val requestBody = DiscordRequest(
            content = title,
            embeds = listOf(
                Embed(
                    title = subTitle,
                    description = content
                )
            )
        )
        restClient.post()
            .uri(CONTRIBUTION_APPLY_URL)
            .body(requestBody)
            .retrieve()
    }

    fun sendAdminAlarm(message: String) {
        val requestBody = DiscordRequest(
            content = message,
            embeds = null
        )
        restClient.post()
            .uri(ADMIN_ALARM_URL)
            .body(requestBody)
            .retrieve()
    }

    fun sendMyFrameApplyAlarm(title: String, subTitle: String, content: String) {
        val requestBody = DiscordRequest(
            content = title,
            embeds = listOf(
                Embed(
                    title = subTitle,
                    description = content
                )
            )
        )
        restClient.post()
            .uri(MY_FRAME_APPLY_URL)
            .body(requestBody)
            .retrieve()
    }


    fun sendOrganizationApplyAlarm(title: String, subTitle: String, content: String) {
        val requestBody = DiscordRequest(
            content = title,
            embeds = listOf(
                Embed(
                    title = subTitle,
                    description = content
                )
            )
        )
        restClient.post()
            .uri(ORGANIZATION_APPLY_URL)
            .body(requestBody)
            .retrieve()
    }

    private fun parseStackTrace(exception: Exception): String {
        val stackTrace = exception.stackTraceToString()
        if (stackTrace.length > 2000) {
            return stackTrace.substring(0, 2000)
        }
        return stackTrace
    }
}