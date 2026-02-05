package com.examen.pedidos_back.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Column(nullable = false)
    private String cliente;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull(message = "El total es obligatorio")
    @PositiveOrZero(message = "El total debe ser mayor o igual a cero")
    @Column(nullable = false)
    private Double total;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false)
    private String estado; // PENDIENTE, PAGADO, CANCELADO
}
