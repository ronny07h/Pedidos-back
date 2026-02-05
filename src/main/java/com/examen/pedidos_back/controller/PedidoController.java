package com.examen.pedidos_back.controller;

import com.examen.pedidos_back.model.Pedido;
import com.examen.pedidos_back.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*") // Allow frontend access
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> create(@Valid @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.createPedido(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll(@RequestParam(required = false) String estado) {
        if (estado != null) {
            return ResponseEntity.ok(pedidoService.getPedidosByEstado(estado));
        }
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @Valid @RequestBody Pedido details) {
        return ResponseEntity.ok(pedidoService.updatePedido(id, details));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/promedio")
    public ResponseEntity<Double> getPromedio() {
        return ResponseEntity.ok(pedidoService.getPromedioTotal());
    }
}
