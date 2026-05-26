package com.origenescolombia.ecommerceapi.service;

import com.origenescolombia.ecommerceapi.dto.DetallePedidoRequestDTO;
import com.origenescolombia.ecommerceapi.dto.DetallePedidoResponseDTO;
import com.origenescolombia.ecommerceapi.model.DetallePedido;
import com.origenescolombia.ecommerceapi.model.Pedido;
import com.origenescolombia.ecommerceapi.model.Producto;
import com.origenescolombia.ecommerceapi.repository.DetallePedidoRepository;
import com.origenescolombia.ecommerceapi.repository.PedidoRepository;
import com.origenescolombia.ecommerceapi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository, PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public List<DetallePedidoResponseDTO> findAll(){
        return detallePedidoRepository.findAll().stream().map(DetallePedidoResponseDTO::desde).collect(Collectors.toList());
    }

    public DetallePedidoResponseDTO findById(Long id){
        DetallePedido item = detallePedidoRepository.findById(id).orElse(null);
        if(item==null) return null;
        return DetallePedidoResponseDTO.desde(item);
    }

    public DetallePedidoResponseDTO save(DetallePedidoRequestDTO dto){
        Pedido pedido=pedidoRepository.findById(dto.getPedidoId()).orElse(null);
        Producto producto = productoRepository.findById(dto.getProductoId()).orElse(null);
        DetallePedido item = new DetallePedido(pedido, dto.getPrecioUnitario(), producto, dto.getCantidad());
        return DetallePedidoResponseDTO.desde(detallePedidoRepository.save(item));
    }
    public DetallePedidoResponseDTO update(Long id, DetallePedidoRequestDTO dto) {
        DetallePedido existente = detallePedidoRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setCantidad(dto.getCantidad());
        existente.setPrecioUnitario(dto.getPrecioUnitario());
        return DetallePedidoResponseDTO.desde(detallePedidoRepository.save(existente));
    }

    public void delete(Long id) {
        detallePedidoRepository.deleteById(id);
    }



}
