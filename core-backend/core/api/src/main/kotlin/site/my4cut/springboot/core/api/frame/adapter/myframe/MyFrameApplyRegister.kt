package site.my4cut.springboot.core.api.frame.adapter.myframe

import site.my4cut.springboot.core.api.config.stereotype.Adapter
import site.my4cut.springboot.db.frame.MyFrameApplyEntity
import site.my4cut.springboot.db.frame.MyFrameApplyJpaRepository

@Adapter
class MyFrameApplyRegister(
    private val myFrameApplyJpaRepository: MyFrameApplyJpaRepository
) {

    fun register(myFrameApplyEntity: MyFrameApplyEntity) : MyFrameApplyEntity {
        return myFrameApplyJpaRepository.save(myFrameApplyEntity)
    }
}