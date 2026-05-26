package com.origenescolombia.ecommerceapi.service;

import com.origenescolombia.ecommerceapi.dto.PedidoRequestDTO;
import com.origenescolombia.ecommerceapi.dto.PedidoResponseDTO;
import com.origenescolombia.ecommerceapi.model.Pedido;
import com.origenescolombia.ecommerceapi.model.Usuario;
import com.origenescolombia.ecommerceapi.repository.PedidoRepository;
import com.origenescolombia.ecommerceapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository){
        this.pedidoRepository=pedidoRepository;
        this.usuarioRepository=usuarioRepository;
    }

    public List<PedidoResponseDTO> findAll(){
        return pedidoRepository.findAll().stream().map(PedidoResponseDTO::desde).collect(Collectors.toList());
    }

    public PedidoResponseDTO findById(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if(pedido==null) return null;
        return PedidoResponseDTO.desde(pedido);
    }
    public PedidoResponseDTO save(PedidoRequestDTO dto){
        Usuario usuario = buscarCliente(dto.getClienteId());
        Pedido pedido = new Pedido(dto.getFecha(), dto.getEstado(), dto.getTotal(), dto.getDireccion_envio(), usuario);
        return PedidoResponseDTO.desde(pedidoRepository.save(pedido));
    }
    public PedidoResponseDTO update(Long id, PedidoRequestDTO dto) {
        Pedido existente = pedidoRepository.findById(id).orElse(null);
        if (existente == null) return null;
        Usuario usuario = buscarCliente(dto.getClienteId());
        existente.setFecha_pedido(dto.getFecha());
        existente.setEstado(dto.getEstado());
        existente.setTotal(dto.getTotal());
        existente.setDireccion_envio(dto.getDireccion_envio());
        existente.setUsuario(usuario);
        return PedidoResponseDTO.desde(pedidoRepository.save(existente));
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    private Usuario buscarCliente(Long clienteId) {
        if (clienteId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "clienteId es obligatorio");
        }
        return usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente no encontrado"));
    }
}

