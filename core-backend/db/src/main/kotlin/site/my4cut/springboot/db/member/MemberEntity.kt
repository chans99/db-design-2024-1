package site.my4cut.springboot.db.member

import jakarta.persistence.*
import site.my4cut.springboot.core.enums.member.MembershipLevel
import site.my4cut.springboot.core.enums.member.SocialType
import site.my4cut.springboot.db.BaseTimeEntity
import java.util.UUID

@Entity
@Table(name = "member")
class MemberEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var userCode: UUID = UUID.randomUUID(),
    var nickname: String? = null,
    var email: String = "",
    @Enumerated(EnumType.STRING)
    var membership: MembershipLevel = MembershipLevel.NORMAL,
    @Enumerated(EnumType.STRING)
    var socialType: SocialType? = null,
    @Column(unique = true)
    var socialId: String? = null,
    var isAdApplied: Boolean = false,
    var isOrganizationRegistered: Boolean = false,
    var organizationName: String? = null,
    @Column(columnDefinition = "TEXT")
    var logoImageUrl: String? = "",
    var phoneNumber: String = "",
    var isDefaultFrameActivated: Boolean = true,
    var refreshToken: String? = null
) : BaseTimeEntity() {
}