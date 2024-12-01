package site.my4cut.springboot.core.api.member.adapter

import org.springframework.stereotype.Service
import site.my4cut.springboot.core.api.exception.CoreException
import site.my4cut.springboot.core.api.exception.EntityNotFoundException
import site.my4cut.springboot.core.enums.member.SocialType
import site.my4cut.springboot.db.member.MemberEntity
import site.my4cut.springboot.db.member.MemberJpaRepository

@Service
class MemberFinder(
    private val memberJpaRepository: MemberJpaRepository
) {
    fun findByIdOrThrow(id: Long) : MemberEntity {
        return memberJpaRepository.findById(id)
                .orElseThrow { EntityNotFoundException("존재하지 않는 사용자입니다. 요청 id : $id") }
    }

    fun findBySocialId(socialId: String) : MemberEntity? {
        return memberJpaRepository.findBySocialId(socialId)
    }

    fun findBySocialTypeAndEmail(socialType: SocialType, email: String) : MemberEntity? {
        return memberJpaRepository.findBySocialTypeAndEmail(socialType, email)
    }

    fun existBySocialId(socialId: String) : Boolean {
        return memberJpaRepository.existsBySocialId(socialId)
    }

    fun existsById(id: Long) : Boolean {
        return memberJpaRepository.existsById(id)
    }

    fun existsBySocialTypeAndEmail(socialType: SocialType, email: String) : Boolean {
        return memberJpaRepository.existsBySocialTypeAndEmail(socialType, email)
    }
}
