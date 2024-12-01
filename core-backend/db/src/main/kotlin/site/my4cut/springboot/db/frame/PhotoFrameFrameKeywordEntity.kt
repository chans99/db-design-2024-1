package site.my4cut.springboot.db.frame

import jakarta.persistence.*

@Entity
@Table(name = "photo_frame_frame_keyword")
class PhotoFrameFrameKeywordEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var photoFrameId: Long? = null,
    var frameKeywordId: Long? = null
) {
}