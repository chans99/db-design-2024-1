package site.my4cut.springboot.db.premiumcode

import org.springframework.data.jpa.repository.JpaRepository

interface PremiumCodeJpaRepository : JpaRepository<PremiumCodeEntity, Long> {
    fun existsByCode(code: String): Boolean
}