package site.my4cut.springboot.core.api.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class FaviconController {

    @GetMapping("/favicon.ico")
    fun favicon(): ResponseEntity<Unit> {
        return ResponseEntity.noContent().build()
    }
}