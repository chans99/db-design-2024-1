package site.my4cut.springboot.db.frame

import org.springframework.data.jpa.repository.JpaRepository

interface FrameKeywordJpaRepository : JpaRepository<FrameKeywordEntity, Long> {
    fun existsByContent(content: String) : Boolean
    fun findByContent(content: String) : FrameKeywordEntity?
    fun findAllBy() : List<FrameKeywordEntity>
}