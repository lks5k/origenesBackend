package com.origenescolombia.ecommerceapi.dto;

import com.origenescolombia.ecommerceapi.model.EstadoPedido;
import com.origenescolombia.ecommerceapi.model.Pedido;

import java.time.LocalDateTime;
import java.time.Period;

public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime fecha;
    private EstadoPedido estado;
    private Long clienteId;
    private String clienteNombre;

    public PedidoResponseDTO() {
    }

    public static PedidoResponseDTO desde(Pedido pedido){
        PedidoResponseDTO dto= new PedidoResponseDTO();
        dto.id=pedido.getId();
        dto.fecha=pedido.getFecha_pedido();
        dto.estado=pedido.getEstado();
        dto.clienteId=pedido.getUsuario().getId();
        dto.clienteNombre=pedido.getUsuario().getNombre();
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }
}
