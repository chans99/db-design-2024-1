package site.my4cut.springboot.db.premiumcode

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import site.my4cut.springboot.db.BaseTimeEntity

@Entity
@Table(name ="premium_code")
class PremiumCodeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var code: String,
) : BaseTimeEntity() {
}