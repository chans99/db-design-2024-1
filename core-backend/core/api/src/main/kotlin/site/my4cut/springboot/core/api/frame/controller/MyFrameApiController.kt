package site.my4cut.springboot.core.api.frame.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.controller.v1.ApiV1Controller
import site.my4cut.springboot.core.api.frame.dto.ApplyMyFrameRequest
import site.my4cut.springboot.core.api.frame.usecase.MyFrameUseCase
import site.my4cut.springboot.core.api.swagger.MyFrameApiDocs

@ApiV1Controller
class MyFrameApiController(
    private val myFrameUseCase: MyFrameUseCase
) : MyFrameApiDocs {

    @PostMapping("/myframes/apply")
    override fun applyFrame(
        @RequestBody request: ApplyMyFrameRequest,
        @Auth memberId: Long
    ) : ResponseEntity<Unit> {
        myFrameUseCase.applyMyFrame(memberId, request)
        return ResponseEntity.noContent().build()
    }
}