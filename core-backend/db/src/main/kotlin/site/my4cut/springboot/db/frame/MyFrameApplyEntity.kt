package site.my4cut.springboot.db.frame

import jakarta.persistence.*
import site.my4cut.springboot.core.enums.frame.FrameApplyType

@Entity
@Table(name = "my_frame_apply")
class MyFrameApplyEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    /*
    신청자 이름(사용자 이름과 다를 수 있음.)
   */
    val name: String? = null,
    /*
    사용자 ID
   */
    val memberId: Long? = null,

    /*
    신청 전화 번호(사용자 번호와 다를 수 있음.)
     */
    val applyPhoneNumber: String? = null,
    @Enumerated(value = EnumType.STRING)
    val frameApplyType: FrameApplyType = FrameApplyType.SELF_MADE,
    /*
    신청 내용
     */
    val content: String? = null,

) {
}