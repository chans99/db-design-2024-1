package site.my4cut.springboot.core.api.admin.adapter

import org.springframework.stereotype.Component
import site.my4cut.springboot.core.api.exception.InvalidRequestException
import site.my4cut.springboot.db.adminmember.AdminMemberJpaRepository

@Component
class AdminMemberFinder(
    private val adminMemberJpaRepository: AdminMemberJpaRepository
) {
    fun existById(id: Long): Boolean {
        return adminMemberJpaRepository.existsById(id)
    }

    fun findByAdminKeyOrThrow(adminKey: String) {
        adminMemberJpaRepository.findByAdminKey(adminKey)
            ?: throw InvalidRequestException("10111")
    }
}