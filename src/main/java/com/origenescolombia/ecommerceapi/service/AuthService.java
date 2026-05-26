package com.origenescolombia.ecommerceapi.service;

import com.origenescolombia.ecommerceapi.dto.AuthResponseDTO;
import com.origenescolombia.ecommerceapi.dto.LoginRequestDTO;
import com.origenescolombia.ecommerceapi.model.Rol;
import com.origenescolombia.ecommerceapi.model.Usuario;
import com.origenescolombia.ecommerceapi.repository.UsuarioRepository;
import com.origenescolombia.ecommerceapi.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        validarEmailYPassword(request.getEmail(), request.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        String token = jwtService.generateToken(usuario);
        return new AuthResponseDTO(token, usuario);
    }

    public AuthResponseDTO registro(Usuario usuario) {
        validarEmailYPassword(usuario.getEmail(), usuario.getPassword());

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está registrado");
        }

        if (usuario.getRol() == null) {
            usuario.setRol(Rol.CLIENTE);
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario guardado = usuarioRepository.save(usuario);

        String token = jwtService.generateToken(guardado);
        return new AuthResponseDTO(token, guardado);
    }

    private void validarEmailYPassword(String email, String password) {
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email y contraseña son obligatorios");
        }
    }
}
