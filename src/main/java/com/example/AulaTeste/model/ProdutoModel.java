package com.example.AulaTeste.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "produto")
public class ProdutoModel {
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "tipo")
    private String tipo;

    @Column(name = "valor")
    private String valor;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public ProdutoModel() {}

    public ProdutoModel(String nome, String tipo, String valor) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }
}
