package yeinyeonha.SMooD.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yeinyeonha.SMooD.dto.*;
import yeinyeonha.SMooD.service.StoreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags = {"가게 관련 API"})
@RequestMapping("/api")
public class StoreController {
    private final StoreService storeService;
    //핵심키워드 대표 가게 정보 조회
    @Operation(summary = "핵심 키워드 대표 가게 정보 조회", description = "스무디 리포트에 들어갈 핵심 키워드별 대표 가게 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 조회 성공", content = @Content(schema = @Schema(implementation = RepresentativeDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/store/representation")
    public ResponseDto<?> findRepresentation(@RequestParam("dong") String dong, @RequestParam("category") String category, @RequestParam("keyword") String keyword) {
        List<RepresentativeDto> responseDto = storeService.findStoreByKeyword(dong, category, keyword);
        return ResponseDto.success("result", responseDto);
    }
    //지역, 업종, 카테고리별 상위 가게 조회
    @Operation(summary = "정렬 기준에 따른 가게 정보 조회", description = "정렬 기준에 맞는 가게 정보들을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가게 정보 조회 성공", content = @Content(schema = @Schema(implementation = StoreInformationDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/store")
    public ResponseDto<?> findStoreBySorting(@RequestParam("sort") String sort, @RequestParam("dong") String dong, @RequestParam("category") String category) {
        List<StoreInformationDto> responseDtoList = storeService.findStoreByCategory(sort, dong, category);
        return ResponseDto.success("result", responseDtoList);
    }
    //가게 상세정보 조회
    @Operation(summary = "가게 상세정보 조회", description = "가게 상세정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가게 상세 정보 조회 성공", content = @Content(schema = @Schema(implementation = StoreDetailDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/store/{storeId}")
    public ResponseDto<?> findStoreDetail(@PathVariable Long storeId) {
        StoreDetailDto responseDto = storeService.findDetailById(storeId);
        return ResponseDto.success("result", responseDto);
    }
    //컨셉 추천 결과값 가져오기
    @Operation(summary = "키워드에 따른 지역 정보 조회", description = "키워드와 업종을 입력하면 키워드가 많이 포함된 지역 3개를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 조회 성공", content = @Content(schema = @Schema(implementation = KeywordTopRegionDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/keyword")
    public ResponseDto<?> findStoreBySorting(@RequestParam("category") String category, @RequestParam("keyword1") String keyword1, @RequestParam(value = "keyword2", required = false) String keyword2, @RequestParam(value = "keyword3", required = false) String keyword3) {
        List<KeywordTopRegionDto> responseDtoList = storeService.findByKeywordAndCategory(category, keyword1, keyword2, keyword3);
        return ResponseDto.success("result", responseDtoList);
    }
    //컨셉 추천 가게정보 가져오기
    @Operation(summary = "지역과 정렬기준에 따른 키워드가 포함된 가게 정보 조회", description = "지역과 업종에 따라서 키워드가 포함된 가게들을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 조회 성공", content = @Content(schema = @Schema(implementation = StoreKeywordInformationDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/keyword/store")
    public ResponseDto<?> findStoreInformationBySorting(@RequestParam("region") String region, @RequestParam("sort") String sorting, @RequestParam("category") String category, @RequestParam("keyword1") String keyword1, @RequestParam(value = "keyword2", required = false) String keyword2, @RequestParam(value = "keyword3", required = false) String keyword3) {
        List<StoreKeywordInformationDto> responseDtoList = storeService.findStoreByKeywordAndCategory(region, sorting, category, keyword1, keyword2, keyword3);
        return ResponseDto.success("result", responseDtoList);
    }
}