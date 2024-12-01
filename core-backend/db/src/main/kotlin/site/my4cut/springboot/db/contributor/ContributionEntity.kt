package site.my4cut.springboot.db.contributor

import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import site.my4cut.springboot.core.enums.contribution.ContributionFeedbackType
import java.time.LocalDateTime

@Entity
@Table(name = "contribution")
class ContributionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val contributorAccount: String = "",
    val idea: String = "",
    @Enumerated(STRING)
    val feedbackType: ContributionFeedbackType = ContributionFeedbackType.IDEA,
    var isApproved: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
}