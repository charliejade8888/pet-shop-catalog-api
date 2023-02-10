package com.tyrell.config

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import java.util.stream.Collectors

class KeycloakRoleConverter :
    Converter<Jwt?, Collection<GrantedAuthority?>?> {
    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val realmAccess =
            jwt.claims["realm_access"] as Map<String, Any>?
        return if (realmAccess == null || realmAccess.isEmpty()) {
            ArrayList()
        } else (realmAccess["roles"] as List<String>?)
            ?.stream()
            ?.map { roleName: String -> "ROLE_$roleName" }
            ?.map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }
            ?.collect(Collectors.toList())!!
    }
}
