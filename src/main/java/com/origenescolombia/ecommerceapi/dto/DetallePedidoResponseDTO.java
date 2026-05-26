package com.origenescolombia.ecommerceapi.dto;

import com.origenescolombia.ecommerceapi.model.DetallePedido;

public class DetallePedidoResponseDTO {
    private Long id;
    private Long pedidoId;
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;

    public DetallePedidoResponseDTO() {
    }
    public static DetallePedidoResponseDTO desde(DetallePedido item){
       DetallePedidoResponseDTO dto = new DetallePedidoResponseDTO();
       dto.id=item.getId();
       if (item.getPedido() != null) {
           dto.pedidoId = item.getPedido().getId();
       }
       if (item.getProducto() != null) {
           dto.productoId = item.getProducto().getId();
           dto.productoNombre = item.getProducto().getNombre();
       }
       dto.cantidad=item.getCantidad();
       dto.precioUnitario=item.getPrecioUnitario();
       return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
