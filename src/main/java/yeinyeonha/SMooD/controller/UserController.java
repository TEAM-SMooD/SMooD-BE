package yeinyeonha.SMooD.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yeinyeonha.SMooD.domain.User;
import yeinyeonha.SMooD.dto.ResponseDto;
import yeinyeonha.SMooD.service.UserService;
@RestController
@RequiredArgsConstructor
@Api(tags = {"회원 관련 API"})
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @Operation(summary = "유저 정보 가져오기", description = "유저 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 조회 성공", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/users")
    public ResponseDto getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());
        return ResponseDto.success("result", user);
    }

    @Operation(summary = "유저 닉네임 변경", description = "유저 닉네임을 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "닉네임 변경 성공", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "정보 없음", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PutMapping("/user")
    public ResponseDto updateNickname(@RequestBody String nickname) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.updateNickname(principal.getUsername(), nickname);
        return ResponseDto.success("result", user);
    }
}


