package site.my4cut.springboot.db.advertisement

import org.springframework.data.jpa.repository.JpaRepository

interface AdvertisementJpaRepository : JpaRepository<AdvertisementEntity, Long>