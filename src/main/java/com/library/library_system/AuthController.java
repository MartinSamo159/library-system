package com.library.library_system;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//Informace o přihlášeném uživateli pro frontend

@RestController
public class AuthController {

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(Authentication authentication) {
        return Map.of(
                "username", authentication.getName(),
                "role", authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst()
                        .orElse("UNKNOWN")
        );
    }
}
