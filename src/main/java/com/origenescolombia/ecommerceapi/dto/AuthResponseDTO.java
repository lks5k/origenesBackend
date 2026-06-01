package com.origenescolombia.ecommerceapi.dto;

import com.origenescolombia.ecommerceapi.model.Rol;
import com.origenescolombia.ecommerceapi.model.Usuario;

public class AuthResponseDTO {
    private Long id;
    private String token;
    private String email;
    private String nombre;
    private Rol rol;

    public AuthResponseDTO() {}

    public AuthResponseDTO(String token, Usuario usuario) {
        this.id     = usuario.getId();
        this.token = token;
        this.email = usuario.getEmail();
        this.nombre = usuario.getNombre();
        this.rol = usuario.getRol();
    }

    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
