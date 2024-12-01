package site.my4cut.springboot.clients.discord

data class DiscordRequest(
    val content: String,
    val embeds: List<Embed>?
)

data class Embed(
    val title: String,
    val description: String
)
