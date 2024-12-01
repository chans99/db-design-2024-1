package site.my4cut.springboot.db.photo

import org.springframework.data.jpa.repository.JpaRepository

interface PhotoLogJpaRepository : JpaRepository<PhotoLogEntity, Long> {

}