package site.my4cut.springboot.db.photo

import org.springframework.data.jpa.repository.JpaRepository

interface PhotoJpaRepository : JpaRepository<PhotoEntity, Long> {
    fun deleteByMemberId(memberId: Long)
    fun findAllByMemberId(memberId: Long): List<PhotoEntity>
    fun findByPhotoCode(photoCode: String) : PhotoEntity?
}