package site.my4cut.springboot.core.api.frame.usecase

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import site.my4cut.springboot.clients.discord.DiscordAlarmService
import site.my4cut.springboot.core.api.config.stereotype.UseCase
import site.my4cut.springboot.core.api.frame.adapter.myframe.MyFrameApplyRegister
import site.my4cut.springboot.core.api.frame.dto.ApplyMyFrameRequest
import site.my4cut.springboot.core.api.member.adapter.MemberFinder
import site.my4cut.springboot.db.frame.MyFrameApplyEntity

@UseCase
@Transactional(readOnly = true)
class MyFrameUseCase(
    private val memberFinder: MemberFinder,
    private val myFrameApplyRegister: MyFrameApplyRegister,
    private val discordAlarmService: DiscordAlarmService
) {

    @Transactional
    fun applyMyFrame(memberId: Long, request: ApplyMyFrameRequest) {
        val memberEntity = memberFinder.findByIdOrThrow(memberId)
        val myFrameApply = MyFrameApplyEntity(
                content = request.content,
                name = request.name,
                memberId = memberEntity.id,
                applyPhoneNumber = request.phoneNumber,
                frameApplyType = request.type
        )
        myFrameApplyRegister.register(myFrameApply)
        discordAlarmService.sendMyFrameApplyAlarm(
            "나만의 프레임 신청이 들어왔습니다. 확인해주세요.",
            "나만의 프레임 신청",
            """
                |신청자 : ${request.name}
                |신청 내용 : ${request.content}
                |신청 타입 : ${request.type}
                |신청자 연락처 : ${request.phoneNumber}
            """
        )
    }
}