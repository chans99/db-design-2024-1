package site.my4cut.springboot.core.api.swagger

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import site.my4cut.springboot.core.api.config.auth.Auth
import site.my4cut.springboot.core.api.photo.dto.PhotoCountResponse
import site.my4cut.springboot.core.api.photo.dto.PhotoSaveResponse
import site.my4cut.springboot.core.api.photo.dto.PhotoVideoSaveResponse
import site.my4cut.springboot.core.api.photo.dto.PhotoUrlResponse

@Tag(name = "[Photo API] 사진 API docs")
interface PhotoApiDocs {

    @Operation(summary = "사진과 비디오 code로 url을 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "조회 성공"),
        ApiResponse(responseCode = "400", description = "잘못된 요청"),
        ApiResponse(responseCode = "500", description = "서버 에러"),
        ApiResponse(responseCode = "401", description = "인증 실패")
    )
    fun getMy4Cut(
        @RequestParam("photoCode", required = true) photoCode: String,
        @RequestParam("videoCode", required = true) videoCode: String
    ) : ResponseEntity<PhotoUrlResponse>

    @Operation(summary = "사진을 업로드합니다.", description = "사진을 저장합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "사진 저장 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러"),
        ApiResponse(responseCode = "401", description = "인증 실패")
    )
    fun savePhotoAndVideo(@RequestPart photo: MultipartFile,
                  @RequestPart video: MultipartFile,
                  @Auth memberId: Long
    ) : ResponseEntity<PhotoVideoSaveResponse>

    @Operation(summary = "전체 사진 촬영수를 조회합니다.", description = "전체 사진 촬영수를 조회합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "전체 사진 촬영 수 조회 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun getPhotoCount(): ResponseEntity<PhotoCountResponse>

    @Operation(summary = "전체 사진 촬영수를 업데이트 합니다 (+1)", description = "전체 사진 촬영수를 업데이트합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "204", description = "전체 사진 촬영 수 업데이트 성공"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun updatePhotoCount(): ResponseEntity<Unit>


    @Operation(summary = "사진 업로드 API", description = "사진을 업로드 합니다.")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "전체 사진 촬영 수 업데이트 성공"),
        ApiResponse(responseCode = "400", description = "잘못된 요청"),
        ApiResponse(responseCode = "500", description = "서버 에러")
    )
    fun savePhoto(
    @RequestPart photo: MultipartFile,
    @Auth memberId: Long
    ) : ResponseEntity<PhotoSaveResponse>
}
