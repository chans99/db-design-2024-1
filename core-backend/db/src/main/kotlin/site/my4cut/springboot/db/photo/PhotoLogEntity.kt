package site.my4cut.springboot.db.photo

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "photo_log")
class PhotoLogEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    var photoCount: Long
) {
}