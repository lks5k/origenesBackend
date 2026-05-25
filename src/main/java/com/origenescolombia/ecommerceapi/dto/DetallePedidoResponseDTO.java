package com.origenescolombia.ecommerceapi.dto;

import com.origenescolombia.ecommerceapi.model.DetallePedido;

public class DetallePedidoResponseDTO {
    private Long id;
    private Long ordenId;
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;

    public DetallePedidoResponseDTO() {
    }
    public static DetallePedidoResponseDTO desde(DetallePedido item){
        
    }
}
