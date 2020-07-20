package ca.zhoozhoo.springcloud.guests.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;

import reactor.core.publisher.Flux;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        // Validate tokens through configured OpenID Provider
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
        // Allow unauthenticated actuator access
        http.authorizeExchange().pathMatchers("/actuator/**").permitAll();
        // Require authentication for all requests
        http.authorizeExchange().anyExchange().authenticated();
        // Allow showing pages within a frame
        http.headers().frameOptions().mode(Mode.SAMEORIGIN);

        return http.build();
    }

    @SuppressWarnings("unchecked")
    private ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        ReactiveJwtAuthenticationConverter jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        // Convert realm_access.roles claims to granted authorities, for use in access decisions
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> Flux
                .fromIterable(((List<String>) ((Map<String, Object>) jwt.getClaims().get("realm_access")).get("roles"))
                        .stream().map(roleName -> "ROLE_" + roleName).map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())));

        return jwtAuthenticationConverter;
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoderByIssuerUri(OAuth2ResourceServerProperties properties) {
        NimbusReactiveJwtDecoder nimbusReactiveJwtDecoder =
                (NimbusReactiveJwtDecoder) ReactiveJwtDecoders.fromIssuerLocation(properties.getJwt().getIssuerUri());
        // Use preferred_username from claims as authentication name, instead of UUID subject
        nimbusReactiveJwtDecoder.setClaimSetConverter(claims -> {
            Map<String, Object> convertedClaims =
                    MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap()).convert(claims);
            convertedClaims.put("sub", (String) convertedClaims.get("preferred_username"));

            return convertedClaims;
        });

        return nimbusReactiveJwtDecoder;
    }
}
