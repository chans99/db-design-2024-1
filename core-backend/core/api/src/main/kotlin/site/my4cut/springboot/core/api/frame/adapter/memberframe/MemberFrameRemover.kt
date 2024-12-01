package site.my4cut.springboot.core.api.frame.adapter.memberframe

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.CoreException
import site.my4cut.springboot.db.frame.MemberPhotoFrameJpaRepository
import site.my4cut.springboot.db.frame.PhotoFrameJpaRepository

@Component
class MemberFrameRemover(
    private val memberFrameJpaRepository: MemberPhotoFrameJpaRepository
) {
    fun remove(memberId: Long, memberFrameId: Long) {
        val memberFrameEntity = memberFrameJpaRepository.findById(memberFrameId)
            .orElseThrow {
                CoreException("존재하지 않는 프레임입니다.")
            }
        if (memberFrameEntity.memberId != memberId) {
            throw CoreException("해당 프레임에 대한 권한이 없습니다.")
        }
        memberFrameJpaRepository.deleteById(memberFrameId)
    }
}