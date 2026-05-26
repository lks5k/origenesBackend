package com.origenescolombia.ecommerceapi.dto;

import com.origenescolombia.ecommerceapi.model.EstadoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoRequestDTO {
    private LocalDateTime fecha;
    private EstadoPedido estado;
    private Long usuarioId;
    private BigDecimal total;
    private String direccion_envio;

    public PedidoRequestDTO() {
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDireccion_envio() {
        return direccion_envio;
    }

    public void setDireccion_envio(String direccion_envio) {
        this.direccion_envio = direccion_envio;
    }
}
