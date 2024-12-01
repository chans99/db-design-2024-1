package site.my4cut.springboot.core.api.frame.adapter.frame

import site.my4cut.springboot.core.api.config.stereotype.Adapter
import site.my4cut.springboot.db.frame.PhotoFrameEntity


@Adapter
class FrameUpdater(
) {
    fun makePublic(frame: PhotoFrameEntity) {
        frame.isPublic = true
    }

    fun increaseDownloadCount(frame: PhotoFrameEntity) {
        frame.increaseDownloadCount()
    }
}