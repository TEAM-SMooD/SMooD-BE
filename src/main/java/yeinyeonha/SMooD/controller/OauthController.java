package yeinyeonha.SMooD.controller;

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
public class OauthController {
    private final OauthService oauthService;
    @GetMapping("/login/oauth/{provider}")
    public ResponseEntity<LoginResponseDto> login(@PathVariable String provider, @RequestParam String code) {
        LoginResponseDto loginResponse = oauthService.login(provider, code);
        return ResponseEntity.ok().body(loginResponse);
    }
}
