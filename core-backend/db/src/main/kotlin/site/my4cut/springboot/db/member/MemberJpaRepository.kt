package site.my4cut.springboot.db.member

import org.springframework.data.jpa.repository.JpaRepository
import site.my4cut.springboot.core.enums.member.SocialType
import java.time.LocalDateTime

interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {
    fun existsBySocialId(socialId: String) : Boolean
    fun findBySocialId(socialId: String) : MemberEntity?

    fun existsBySocialTypeAndEmail(socialType: SocialType, email: String) : Boolean

    fun findBySocialTypeAndEmail(socialTyp: SocialType, email: String) : MemberEntity?

    fun countByCreatedDateBetween(start: LocalDateTime, end: LocalDateTime) : Long
}