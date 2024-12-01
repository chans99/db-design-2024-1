package site.my4cut.springboot.core.api.frame.adapter.memberframe

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.EntityNotFoundException
import site.my4cut.springboot.db.frame.MemberPhotoFrameEntity
import site.my4cut.springboot.db.frame.MemberPhotoFrameJpaRepository

@Component
class MemberFrameFinder(
    private val memberFrameJpaRepository: MemberPhotoFrameJpaRepository,
) {

    fun findByMemberId(memberId: Long) : List<MemberPhotoFrameEntity> {
        return memberFrameJpaRepository.findByMemberIdOrderByFrameOrderAsc(memberId)
    }

    fun findByIdOrThrow(id: Long) : MemberPhotoFrameEntity {
        return memberFrameJpaRepository.findById(id)
                .orElseThrow {
                    EntityNotFoundException("존재하지 않는 프레임입니다. id : $id")
                }
    }

    fun existsByMemberIdAndPhotoFrameId(memberId: Long, photoFrameId: Long) : Boolean {
        return memberFrameJpaRepository.existsByMemberIdAndPhotoFrameId(memberId, photoFrameId)
    }

}