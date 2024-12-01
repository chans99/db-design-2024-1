package site.my4cut.springboot.core.api.premiumcode.adapter

import site.my4cut.springboot.core.api.config.stereotype.Adapter
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.db.premiumcode.PremiumCodeJpaRepository

@Adapter
class PremiumCodeValidator(
    private val premiumCodeJpaRepository: PremiumCodeJpaRepository
) {

    fun validate(code: String) : Boolean {
        return premiumCodeJpaRepository.existsByCode(code)
    }
}