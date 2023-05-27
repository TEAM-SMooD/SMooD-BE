package yeinyeonha.SMooD.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yeinyeonha.SMooD.dto.KeywordTopRegionDto;
import yeinyeonha.SMooD.dto.ResponseDto;
import yeinyeonha.SMooD.dto.StoreAnalysisDto;
import yeinyeonha.SMooD.dto.StoreInformationDto;
import yeinyeonha.SMooD.service.StoreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags = {"가게 관련 API"})
@RequestMapping("/api")
public class StoreController {
    private final StoreService storeService;
    //지역과 카테고리에 따른 상권분석 태블로 정보 조회
//    @Operation(summary = "리포트 태블로 정보 조회", description = "스무디 리포트에 들어갈 태블로 정보들을 조회합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "정보 조회 성공", content = @Content(schema = @Schema(implementation = StoreAnalysisDto.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
//            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
//            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
//    })
//    @GetMapping("/store")
//    public ResponseDto<?> findTableau(@RequestParam("dong") String dong, @RequestParam("category") String category) {
//        StoreAnalysisDto responseDto = storeService.findAnalysis(dong, category);
//        return ResponseDto.success("result", responseDto);
//    }
//    //카테고리별 가게 정보 가져오기
//    @Operation(summary = "정렬에 따른 가게 정보 조회", description = "정렬 기준에 맞는 가게 정보들을 가져옵니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "가게 정보 조회 성공", content = @Content(schema = @Schema(implementation = StoreInformationDto.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
//            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
//            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
//    })
//    @GetMapping("/store/{sortId}")
//    public ResponseDto<?> findStoreBySorting(@RequestParam("sort") String sort, @RequestParam("dong") String dong, @RequestParam("category") String category) {
//        List<StoreInformationDto> responseDtoList = storeService.findStoreByCategory(sort, dong, category);
//        return ResponseDto.success("result", responseDtoList);
//    }
    //컨셉 추천 결과값 가져오기
    @Operation(summary = "키워드에 따른 지역 정보 가져오기", description = "키워드와 업종을 입력하면 키워드가 많이 포함된 지역 3개를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 조회 성공", content = @Content(schema = @Schema(implementation = KeywordTopRegionDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/keyword")
    public ResponseDto<?> findStoreBySorting(@RequestParam("category") String category, @RequestParam("keyword1") String keyword1, @RequestParam(value = "keyword2", required = false) String keyword2, @RequestParam(value = "keyword3", required = false) String keyword3) {
        List<KeywordTopRegionDto> result = storeService.findByKeywordAndCategory(category, keyword1, keyword2, keyword3);
        return ResponseDto.success("result", result);
    }
}
