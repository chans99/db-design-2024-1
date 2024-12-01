package site.my4cut.springboot.db.adminmember

import org.springframework.data.jpa.repository.JpaRepository

interface AdminMemberJpaRepository : JpaRepository<AdminMemberEntity, Long> {
    fun findByAdminKey(adminKey: String) : AdminMemberEntity?
    fun existsByAdminKey(adminKey: String) : Boolean
}