package site.my4cut.springboot.db.frame

import jakarta.persistence.*

@Entity
@Table(name = "member_photo_frame")
class MemberPhotoFrameEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val memberId: Long? = null,
    val photoFrameId: Long? = null,

    /*
    * 프레임 정렬 순서
     */
    var frameOrder: Long? = null,

    /*
    * 숨김 여부
     */
    var isHidden: Boolean = false
) {
}