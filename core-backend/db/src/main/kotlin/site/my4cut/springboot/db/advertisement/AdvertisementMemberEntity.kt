package site.my4cut.springboot.db.advertisement;

import jakarta.persistence.*

// 광고 데이터 v2.0 기준에는 서버에서 필요 없음.
@Entity
@Table(name = "advertisement_member")
class AdvertisementMemberEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val advertisementId: Long? = null,
    val memberId: Long? = null,
) {
}
