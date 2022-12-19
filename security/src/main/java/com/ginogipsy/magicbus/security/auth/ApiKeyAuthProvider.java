package com.ginogipsy.magicbus.security.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author francesco.grossi
 */
@Component
public class ApiKeyAuthProvider implements AuthenticationProvider {

    @Value("${app.security.apiKey.headerName}")
    private String apiKeyHeaderName;

    @Value("${app.security.apiKey.value}")
    private String apiKeyValue;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKeyOnRequest = (String) authentication.getPrincipal();

        if (ObjectUtils.isEmpty(apiKeyOnRequest)) {
            throw new InsufficientAuthenticationException(apiKeyHeaderName + " not found in request header");
        } else {
            if (this.apiKeyValue.equals(apiKeyOnRequest)) {
                return new ApiKeyAuthToken(apiKeyOnRequest, true);
            }
            throw new BadCredentialsException(apiKeyHeaderName + " in request header is not valid");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthToken.class.isAssignableFrom(authentication);
    }
}
