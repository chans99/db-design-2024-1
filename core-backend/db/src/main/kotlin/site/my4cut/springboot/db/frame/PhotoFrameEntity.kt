package site.my4cut.springboot.db.frame

import jakarta.persistence.*
import site.my4cut.springboot.db.BaseTimeEntity

@Entity
@Table(name = "photo_frame")
class PhotoFrameEntity(

    /*
    * PK
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    /*
    * 프레임 제목
     */
    var title: String? = null,

    /*
    * 공개유무
     */
    var isPublic: Boolean = false,

    /*
    * 프레임 설명
     */
    var description: String? = null,

    /*
    * 이미지 URL
     */
    @Column(columnDefinition = "TEXT")
    var imageUrl: String? = null,

    /*
    * 섬네일 URL
     */
    @Column(columnDefinition = "TEXT")
    var thumbnailImageUrl: String? = null,

    /*
    * 다운로드 횟수
     */
    var downloadCount: Long = 0,

    /*
    * 프레임 조회수
     */
    var viewCount: Long = 0,

    /*
    * 만든 사람
     */
    var makerName: String? = null

) : BaseTimeEntity() {

    fun increaseViewCount() {
        this.viewCount += 1
    }

    fun increaseDownloadCount() {
        this.downloadCount += 1
    }
}