package com.ginogipsy.magicbus.security.auth;

import org.springframework.stereotype.Component;
import org.springframework.security.authentication.ProviderManager;

import java.util.Collections;

/**
 * @author francesco.grossi
 */
@Component
public class ApiKeyAuthManager extends ProviderManager {
    public ApiKeyAuthManager(ApiKeyAuthProvider apiKeyAuthProvider) {
        super(Collections.singletonList(apiKeyAuthProvider));
    }
}
