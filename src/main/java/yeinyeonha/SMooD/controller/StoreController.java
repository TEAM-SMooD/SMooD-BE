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
import yeinyeonha.SMooD.dto.StoreResponseDto;
import yeinyeonha.SMooD.service.StoreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags = {"가게 관련 API"})
@RequestMapping("/api")
public class StoreController {
    private final StoreService storeService;
    //카테고리별 가게 정보 가져오기
    @Operation(summary = "가게정보 가져하기", description = "정렬 기준에 맞는 가게 정보들을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가게 정보 조회 성공", content = @Content(schema = @Schema(implementation = StoreResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/store")
    public ResponseDto<?> findStoreBySorting(@RequestParam("sort") String sort, @RequestParam("dong") String dong, @RequestParam("category") String category) {
        List<StoreResponseDto> responseDtoList = storeService.findStoreByCategory(sort, dong, category);
        return ResponseDto.success("result", responseDtoList);
    }
}
