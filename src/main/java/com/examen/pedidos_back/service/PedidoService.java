package com.examen.pedidos_back.service;

import com.examen.pedidos_back.model.Pedido;
import com.examen.pedidos_back.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido updatePedido(Long id, Pedido details) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
        
        pedido.setCliente(details.getCliente());
        pedido.setFecha(details.getFecha());
        pedido.setTotal(details.getTotal());
        pedido.setEstado(details.getEstado());
        
        return pedidoRepository.save(pedido);
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> getPedidosByEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public Double getPromedioTotal() {
        Double avg = pedidoRepository.calculateAverageTotal();
        return (avg != null) ? avg : 0.0;
    }
}
