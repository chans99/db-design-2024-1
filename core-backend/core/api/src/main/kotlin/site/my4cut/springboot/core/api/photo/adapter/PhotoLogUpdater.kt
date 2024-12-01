package site.my4cut.springboot.core.api.photo.adapter

import site.my4cut.springboot.core.api.config.stereotype.Adapter
import site.my4cut.springboot.db.photo.PhotoLogEntity

@Adapter
class PhotoLogUpdater {
    fun update(photoLogEntity: PhotoLogEntity) {
        photoLogEntity.photoCount += 1
    }
}