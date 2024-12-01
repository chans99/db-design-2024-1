package site.my4cut.springboot.core.api.frame.adapter.frame

import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.config.stereotype.Adapter
import site.my4cut.springboot.infra.aws.S3Service

@Adapter
class FrameUploader(
    private val s3Service: S3Service
) {
    companion object {
        private const val STORE_FRAME_DIRECTORY_PATH = "storeframes/"
        private const val PRIVATE_FRAME_DIRECTORY_PATH = "privateframes/"
    }


    // TODO: 추가 구현 필요함.
    fun upload(frameImage: MultipartFile){
        s3Service.uploadFile(STORE_FRAME_DIRECTORY_PATH, frameImage)
    }

}