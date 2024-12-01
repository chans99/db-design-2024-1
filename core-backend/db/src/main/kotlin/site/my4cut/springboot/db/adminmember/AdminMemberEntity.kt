package site.my4cut.springboot.db.adminmember

import jakarta.persistence.*

@Entity
@Table(name = "admin_member")
class AdminMemberEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var adminKey: String? = null
) {
}