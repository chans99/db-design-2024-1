package site.my4cut.springboot.db.frame

import org.springframework.data.jpa.repository.JpaRepository

interface PhotoFrameFrameKeywordJpaRepository : JpaRepository<PhotoFrameFrameKeywordEntity, Long> {
    fun findByFrameKeywordId(keywordId: Long): List<PhotoFrameFrameKeywordEntity>
    fun findByPhotoFrameId(photoFrameId: Long): List<PhotoFrameFrameKeywordEntity>

    fun existsByPhotoFrameIdAndFrameKeywordId(photoFrameId: Long, frameKeywordId: Long): Boolean
}