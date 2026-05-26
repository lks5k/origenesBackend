package com.origenescolombia.ecommerceapi.controller;

import com.origenescolombia.ecommerceapi.dto.DetallePedidoRequestDTO;
import com.origenescolombia.ecommerceapi.dto.DetallePedidoResponseDTO;
import com.origenescolombia.ecommerceapi.service.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-pedidos")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public List<DetallePedidoResponseDTO> listar() {
        return detallePedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO> obtener(@PathVariable Long id) {
        DetallePedidoResponseDTO detalle = detallePedidoService.findById(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<DetallePedidoResponseDTO> crear(@Valid @RequestBody DetallePedidoRequestDTO detalle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detallePedidoService.save(detalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO> actualizar(@PathVariable Long id,
                                                               @Valid @RequestBody DetallePedidoRequestDTO detalle) {
        DetallePedidoResponseDTO actualizado = detallePedidoService.update(id, detalle);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (detallePedidoService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        detallePedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
