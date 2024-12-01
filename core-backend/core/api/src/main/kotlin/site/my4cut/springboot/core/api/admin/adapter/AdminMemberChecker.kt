package site.my4cut.springboot.core.api.admin.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.db.adminmember.AdminMemberJpaRepository

@Component
class AdminMemberChecker(
    private val adminMemberJpaRepository: AdminMemberJpaRepository
) {

    fun check(adminKey: String) : Boolean {
        return adminMemberJpaRepository.existsByAdminKey(adminKey)
    }
}