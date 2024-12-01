package site.my4cut.springboot.db.frame

import org.springframework.data.jpa.repository.JpaRepository

interface MemberPhotoFrameJpaRepository : JpaRepository<MemberPhotoFrameEntity, Long> {
    fun deleteByMemberId(memberId: Long)
    fun findByMemberId(memberId: Long): List<MemberPhotoFrameEntity>
    fun findByMemberIdOrderByFrameOrderAsc(memberId: Long): List<MemberPhotoFrameEntity>

    fun countByMemberId(memberId: Long): Long

    fun existsByMemberIdAndPhotoFrameId(memberId: Long, photoFrameId: Long): Boolean
}