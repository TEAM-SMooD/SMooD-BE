package yeinyeonha.SMooD.oauth.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import yeinyeonha.SMooD.oauth.token.AuthToken;
import yeinyeonha.SMooD.oauth.token.AuthTokenProvider;
import yeinyeonha.SMooD.util.HeaderUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final AuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)  throws ServletException, IOException {
        String tokenStr = HeaderUtil.getAccessToken(request);
        AuthToken token = tokenProvider.convertAuthToken(tokenStr);
        if (token.validate()) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}

