package site.my4cut.springboot.core.api.frame.adapter.memberframe

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.frame.MemberPhotoFrameEntity
import site.my4cut.springboot.db.frame.MemberPhotoFrameJpaRepository

@Component
class MemberFrameRegister(
    private val memberPhotoFrameJpaRepository: MemberPhotoFrameJpaRepository
) {
    fun register(memberPhotoFrameEntity: MemberPhotoFrameEntity) : MemberPhotoFrameEntity {
        return memberPhotoFrameJpaRepository.save(memberPhotoFrameEntity)
    }
}