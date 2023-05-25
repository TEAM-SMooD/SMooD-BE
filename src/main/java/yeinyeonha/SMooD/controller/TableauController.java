package yeinyeonha.SMooD.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yeinyeonha.SMooD.dto.ResponseDto;
import yeinyeonha.SMooD.dto.TableauRequestDto;
import yeinyeonha.SMooD.dto.TableauResponseDto;
import yeinyeonha.SMooD.service.RegionCategoryService;

@RequiredArgsConstructor
@RestController
@Api(tags = {"태블로 관련 API"})
@RequestMapping("/api")
public class TableauController {
    private final RegionCategoryService regionCategoryService;
    //지역과 카테고리별 태블로 url 가져오기
    @Operation(summary = "게시글 작성하기", description = "게시글을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 작성 성공", content = @Content(schema = @Schema(implementation = TableauResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/tableau")
    public ResponseDto<?> findTableau(@RequestBody TableauRequestDto requestDto) {
        TableauResponseDto tableau = regionCategoryService.findTableauByRegionAndCategory(requestDto);
        return ResponseDto.success("result", tableau);
    }
}
