package com.examen.pedidos_back.repository;

import com.examen.pedidos_back.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstado(String estado);

    @Query("SELECT AVG(p.total) FROM Pedido p")
    Double calculateAverageTotal();
}
