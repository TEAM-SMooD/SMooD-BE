package yeinyeonha.SMooD.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yeinyeonha.SMooD.dto.LoginResponseDto;
import yeinyeonha.SMooD.service.OauthService;

@RestController
@RequiredArgsConstructor
@Api(tags = "login")
public class OauthController {
    private final OauthService oauthService;
    @ApiOperation(
            value = "카카오 로그인",
            notes = "카카오 로그인 API")
    @ApiResponses({
            @ApiResponse(code=200, message="성공"),
            @ApiResponse(code=401, message="접근 권한이 없습니다.")
    })
    @GetMapping("/login/oauth/{provider}")
    public ResponseEntity<LoginResponseDto> login(@PathVariable String provider, @RequestParam String code) {
        LoginResponseDto loginResponse = oauthService.login(provider, code);
        return ResponseEntity.ok().body(loginResponse);
    }
}
