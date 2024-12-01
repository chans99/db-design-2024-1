package site.my4cut.springboot.db.frame

import org.springframework.data.jpa.repository.JpaRepository

interface MyFrameApplyJpaRepository : JpaRepository<MyFrameApplyEntity, Long> {
}