package com.example.AulaTeste.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "pedido")
public class PedidoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID numero_pedido;

    @Column(name = "valor_pedido")
    private String valor_pedido;
    
    @Column(name = "cliente")
    private String cliente;


    @CreationTimestamp
    private LocalDateTime createdAt;

    public PedidoModel() {}

    public PedidoModel(String valor_pedido, String cliente) {
        this.valor_pedido = valor_pedido;
        this.cliente = cliente;
    
    }
}

