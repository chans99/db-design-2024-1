package site.my4cut.springboot.db.advertisement

import jakarta.persistence.*
import site.my4cut.springboot.core.enums.advertisement.AdvertisementType

// 광고 데이터 v2.0 기준에는 서버에서 필요 없음.
@Entity
@Table(name = "advertisement")
class AdvertisementEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val type: AdvertisementType = AdvertisementType.BANNER,
    var adId: String? = null,
) {
}