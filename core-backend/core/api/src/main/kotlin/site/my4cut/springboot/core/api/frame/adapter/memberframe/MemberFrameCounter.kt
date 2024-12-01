package site.my4cut.springboot.core.api.frame.adapter.memberframe

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.frame.MemberPhotoFrameJpaRepository

@Component
class MemberFrameCounter(
    private val memberPhotoFrameJpaRepository: MemberPhotoFrameJpaRepository
) {
    fun countByMemberId(memberId: Long) : Long {
        return memberPhotoFrameJpaRepository.countByMemberId(memberId)
    }
}