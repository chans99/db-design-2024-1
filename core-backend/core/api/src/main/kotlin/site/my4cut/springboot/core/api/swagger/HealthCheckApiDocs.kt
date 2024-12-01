package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "[Health Check API] Health Check")
interface HealthCheckApiDocs {

    @Operation(summary = "Health Check", description = "항상 MY4CUT을 반환합니다.")
    fun healthCheck(): ResponseEntity<String>
}