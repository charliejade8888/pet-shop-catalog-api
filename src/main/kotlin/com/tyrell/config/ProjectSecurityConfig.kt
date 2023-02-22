package com.tyrell.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
class ProjectSecurityConfig {

    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder? {
        return ReactiveJwtDecoders.fromIssuerLocation("http://localhost:8180/realms/petshoprealm");
    }

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        http.authorizeExchange()
            .pathMatchers("/catalog/delete/**").hasAnyRole("USER")
            .pathMatchers("/catalog/add/**").permitAll()
            .pathMatchers("/webjars/swagger-ui/**", "/v3/api-docs.yaml", "/v3/api-docs", "/v3/api-docs/swagger-config").permitAll()
            .pathMatchers("/swagger-ui.html").permitAll()
            .pathMatchers("/actuator/**").permitAll()
            .pathMatchers("/catalog/update/**").permitAll()
            .pathMatchers("/catalog/search/**").authenticated()
            .pathMatchers("/catalog/list/**").hasAnyRole("USER", "ADMIN")
            .and()
            .csrf()
            .disable()
            .oauth2ResourceServer().jwt().jwtAuthenticationConverter(grantedAuthoritiesExtractor());
        return http.build()
    }

    fun grantedAuthoritiesExtractor(): Converter<Jwt?, Mono<AbstractAuthenticationToken?>?>? {
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(KeycloakRoleConverter())
        return ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:4200")
        configuration.addAllowedHeader("*")
        configuration.addAllowedMethod("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
