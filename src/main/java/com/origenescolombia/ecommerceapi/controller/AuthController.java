package com.origenescolombia.ecommerceapi.controller;

import com.origenescolombia.ecommerceapi.dto.AuthResponseDTO;
import com.origenescolombia.ecommerceapi.dto.LoginRequestDTO;
import com.origenescolombia.ecommerceapi.model.Usuario;
import com.origenescolombia.ecommerceapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthResponseDTO> registro(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registro(usuario));
    }
}
