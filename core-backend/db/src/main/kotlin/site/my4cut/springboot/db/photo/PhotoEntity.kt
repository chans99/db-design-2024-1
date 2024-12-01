package site.my4cut.springboot.db.photo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import site.my4cut.springboot.db.BaseTimeEntity

@Entity
@Table(name = "photo")
class PhotoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val memberId: Long? = null,
    val photoCode: String? = null,
    val videoCode: String? = null,
    @Column(columnDefinition = "TEXT")
    val videoUrl: String? = null,
    @Column(columnDefinition = "TEXT")
    val imageUrl: String? = null
) : BaseTimeEntity() {
}