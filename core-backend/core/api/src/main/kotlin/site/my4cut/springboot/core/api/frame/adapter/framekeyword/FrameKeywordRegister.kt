package site.my4cut.springboot.core.api.frame.adapter.framekeyword

import org.springframework.stereotype.Component
import site.my4cut.springboot.db.frame.FrameKeywordEntity
import site.my4cut.springboot.db.frame.FrameKeywordJpaRepository

@Component
class FrameKeywordRegister(
    private val frameKeywordJpaRepository: FrameKeywordJpaRepository
) {
    fun register(entity: FrameKeywordEntity) : FrameKeywordEntity {
        return frameKeywordJpaRepository.save(entity)
    }
}