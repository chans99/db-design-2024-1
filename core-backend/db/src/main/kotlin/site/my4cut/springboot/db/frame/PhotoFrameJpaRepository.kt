package site.my4cut.springboot.db.frame

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PhotoFrameJpaRepository : JpaRepository<PhotoFrameEntity, Long> {
    fun findByIsPublicIsTrue(pageable: Pageable): List<PhotoFrameEntity>
    fun findByIsPublicIsTrueAndIdIn(ids: List<Long?>, pageable: Pageable): List<PhotoFrameEntity>

}