package com.origenescolombia.ecommerceapi.service;

import com.origenescolombia.ecommerceapi.model.Usuario;
import com.origenescolombia.ecommerceapi.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario cliente) {
        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email es obligatorio");
        }
        if (usuarioRepository.existsByEmail(cliente.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está registrado");
        }
        if (cliente.getPassword() != null && !cliente.getPassword().isBlank()) {
            cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        }
        return usuarioRepository.save(cliente);
    }

    public Usuario update(Long id, Usuario datos) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setNombre(datos.getNombre());
        existente.setEmail(datos.getEmail());
        existente.setTelefono(datos.getTelefono());
        existente.setRol(datos.getRol());
        return usuarioRepository.save(existente);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
