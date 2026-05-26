package com.origenescolombia.ecommerceapi.dto;

import com.origenescolombia.ecommerceapi.model.EstadoPedido;
import com.origenescolombia.ecommerceapi.model.Pedido;

import java.time.LocalDateTime;
import java.time.Period;

public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime fecha;
    private EstadoPedido estado;
    private Long usuarioId;
    private String usuarioNombre;

    public PedidoResponseDTO() {
    }

    public static PedidoResponseDTO desde(Pedido pedido){
        PedidoResponseDTO dto= new PedidoResponseDTO();
        dto.id=pedido.getId();
        dto.fecha=pedido.getFecha_pedido();
        dto.estado=pedido.getEstado();
        dto.usuarioId=pedido.getUsuario().getId();
        dto.usuarioNombre=pedido.getUsuario().getNombre();
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
}
