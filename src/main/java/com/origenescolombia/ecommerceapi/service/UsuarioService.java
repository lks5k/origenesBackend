package com.origenescolombia.ecommerceapi.service;

import com.origenescolombia.ecommerceapi.model.Usuario;
import com.origenescolombia.ecommerceapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }
    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario cliente){
        return usuarioRepository.save(cliente);
    }

    public Usuario update(Long id, Usuario datos) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setEmail(datos.getEmail());
        existente.setTelefono(datos.getTelefono());
        existente.setDireccion(datos.getDireccion());
        existente.setRol(datos.getRol());
        return usuarioRepository.save(existente);
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }
}
