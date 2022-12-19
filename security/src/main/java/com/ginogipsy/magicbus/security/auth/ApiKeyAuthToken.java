package com.ginogipsy.magicbus.security.auth;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * @author francesco.grossi
 */
public class ApiKeyAuthToken extends AbstractAuthenticationToken {

    private final String apiKey;

    public ApiKeyAuthToken(final String apiKey, boolean authenticated) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.apiKey = apiKey;
        setAuthenticated(authenticated);
    }

    public ApiKeyAuthToken(String apiKey) {
        this(apiKey, false);
    }

    public ApiKeyAuthToken() {
        this(null, false);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}
