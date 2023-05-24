package yeinyeonha.SMooD.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yeinyeonha.SMooD.oauth.token.AuthTokenProvider;

import java.util.Base64;

@Configuration
@Slf4j
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public AuthTokenProvider jwtProvider() {
        log.info(secret);
        return new AuthTokenProvider(secret);
    }
}
