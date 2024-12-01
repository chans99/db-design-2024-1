package site.my4cut.springboot.core.api.member.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.member.MemberEntity
import site.my4cut.springboot.db.member.MemberJpaRepository

@Component
class MemberRegister(
    private val memberJpaRepository: MemberJpaRepository
) {
    fun register(memberEntity: MemberEntity) : MemberEntity {
        return memberJpaRepository.save(memberEntity)
    }
}