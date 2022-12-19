package com.ginogipsy.magicbus.security.auth;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author francesco.grossi
 */
@Component
@Slf4j
public class ApiKeyAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final String apiKeyHeaderName;

    public ApiKeyAuthFilter(ApiKeyAuthManager apiKeyAuthManager,
                            @Value("${app.security.apiKey.filterPattern}") String apiKeyFilterPattern,
                            @Value("${app.security.apiKey.headerName}") String apiKeyHeaderName) {
        super(apiKeyFilterPattern);
        this.setAuthenticationManager(apiKeyAuthManager);
        this.apiKeyHeaderName = apiKeyHeaderName;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Optional<String> apiKeyHeader = Optional.ofNullable(request.getHeader(this.apiKeyHeaderName));

        ApiKeyAuthToken apiKeyToken = apiKeyHeader.map(ApiKeyAuthToken::new).orElse(new ApiKeyAuthToken());

        return getAuthenticationManager().authenticate(apiKeyToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(authResult);
        log.info("ApiKeyAuthFilter - successfulAuthentication() -> Successfully authenticated!");
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        final BeErrorCodeEnum beErrorCodeEnum = BeErrorCodeEnum.LOGIN_FAILED;
        log.error("ApiKeyAuthFilter - unsuccessfulAuthentication() -> Authentication failed!");
        final Error error = new Error(beErrorCodeEnum.getCode(), failed.getMessage());
        final String responseBody = new JsonMapper().writeValueAsString(error);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(beErrorCodeEnum.getHttpStatus().value());
        response.getWriter().write(responseBody);
    }

    private record Error(String code, String message) {}
}
