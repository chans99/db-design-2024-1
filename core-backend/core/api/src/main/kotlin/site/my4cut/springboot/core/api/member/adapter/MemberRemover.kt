package site.my4cut.springboot.core.api.member.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.frame.MemberPhotoFrameJpaRepository
import site.my4cut.springboot.db.member.MemberEntity
import site.my4cut.springboot.db.member.MemberJpaRepository
import site.my4cut.springboot.db.photo.PhotoJpaRepository

@Component
class MemberRemover(
    private val memberJpaRepository: MemberJpaRepository,
    private val photoJpaRepository: PhotoJpaRepository,
    private val memberPhotoFrameJpaRepository: MemberPhotoFrameJpaRepository
) {

    fun remove(member: MemberEntity) {
        // 사진 삭제
        photoJpaRepository.deleteByMemberId(member.id!!)
        // 사용자 보유 프레임 삭제
        memberPhotoFrameJpaRepository.deleteByMemberId(member.id!!)
        // 프레임 정보 삭제
        memberJpaRepository.deleteById(member.id!!)
    }
}