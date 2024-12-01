package site.my4cut.springboot.core.api.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import site.my4cut.springboot.core.api.swagger.HealthCheckApiDocs

@RestController
class HealthCheckController : HealthCheckApiDocs {

    @GetMapping("/health")
    override fun healthCheck(): ResponseEntity<String> = ResponseEntity.ok("MY4CUT")

}