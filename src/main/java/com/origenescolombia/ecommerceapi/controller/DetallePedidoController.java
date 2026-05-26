package com.origenescolombia.ecommerceapi.controller;

import com.origenescolombia.ecommerceapi.dto.DetallePedidoRequestDTO;
import com.origenescolombia.ecommerceapi.dto.DetallePedidoResponseDTO;
import com.origenescolombia.ecommerceapi.service.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-pedido")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    @Autowired

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public ResponseEntity<List<DetallePedidoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(detallePedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO> obtenerPorId(@PathVariable Long id) {
        DetallePedidoResponseDTO item = detallePedidoService.findById(id);
        if (item == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<DetallePedidoResponseDTO> crear(@Valid @RequestBody DetallePedidoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detallePedidoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO> actualizar(@PathVariable Long id,
                                                           @Valid @RequestBody DetallePedidoRequestDTO dto) {
        DetallePedidoResponseDTO actualizado = detallePedidoService.update(id, dto);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detallePedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
