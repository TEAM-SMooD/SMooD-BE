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
import yeinyeonha.SMooD.dto.StoreAnalysisDto;
import yeinyeonha.SMooD.dto.TableauResponseDto;
import yeinyeonha.SMooD.service.RegionCategoryService;

@RequiredArgsConstructor
@RestController
@Api(tags = {"태블로 관련 API"})
@RequestMapping("/api")
public class TableauController {
    private final RegionCategoryService regionCategoryService;
    //지역과 카테고리별 태블로 url 가져오기
    @Operation(summary = "지역별 상권분석 정보 조회", description = "지역별 상권분석 정보 태블로 url을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "태블로 Url 조회 성공", content = @Content(schema = @Schema(implementation = TableauResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/tableau")
    public ResponseDto<?> findTableau(@RequestParam("dong") String dong, @RequestParam("category") String category) {
        TableauResponseDto tableau = regionCategoryService.findTableauByRegionAndCategory(dong, category);
        return ResponseDto.success("result", tableau);
    }
    //지역과 카테고리에 따른 상권분석 태블로 정보 조회
    @Operation(summary = "리포트 태블로 정보 조회", description = "스무디 리포트에 들어갈 태블로 정보들을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 조회 성공", content = @Content(schema = @Schema(implementation = StoreAnalysisDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/report")
    public ResponseDto<?> findAnalysisTableau(@RequestParam("dong") String dong, @RequestParam("category") String category) {
        StoreAnalysisDto responseDto = regionCategoryService.findAnalysis(dong, category);
        return ResponseDto.success("result", responseDto);
    }
}
