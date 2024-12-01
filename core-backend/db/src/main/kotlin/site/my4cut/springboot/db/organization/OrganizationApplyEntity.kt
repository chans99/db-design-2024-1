package site.my4cut.springboot.db.organization

import jakarta.persistence.*

@Entity
@Table(name = "organization_apply")
class OrganizationApplyEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val applicationName: String = "",
    val organizationName: String = "",
    val description: String = "",
    var memberId: Long? = null,
    var phone: String = ""
) {
}