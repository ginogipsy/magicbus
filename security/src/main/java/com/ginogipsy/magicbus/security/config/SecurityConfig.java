package com.ginogipsy.magicbus.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import com.ginogipsy.magicbus.security.auth.ApiKeyAuthManager;
import com.ginogipsy.magicbus.security.auth.ApiKeyAuthFilter;

/**
 * @author francesco.grossi
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApiKeyAuthManager apiKeyAuthManager;
    private final ApiKeyAuthFilter apiKeyAuthFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // .sessionManagement()
                // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // .and()
                // .csrf()
                // .disable()
                .authorizeRequests().anyRequest().authenticated().and()
                .addFilterBefore(apiKeyAuthFilter, AnonymousAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() {
        return this.apiKeyAuthManager;
    }
}
