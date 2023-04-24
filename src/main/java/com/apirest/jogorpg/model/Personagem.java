package com.apirest.jogorpg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PERSONAGEM")
@Entity
public class Personagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "TIPO", nullable = false)
    private String tipo;
    @Column(name = "QTD_VIDAS", nullable = false)
    private int qtdVidas;
    @Column(name = "PODER")
    private int poder;
    @Column(name = "DEFESA")
    private int defesa;
    @Column(name = "AGILIDADE")
    private int agilidade;
    @Column(name = "QTD_DADOS", nullable = false)
    private int qtdDado;
    @Column(name = "TOTAL_FACES", nullable = false)
    private int tolalFaces;

    public Personagem(String tipo, int qtdVidas, int poder, int defesa, int agilidade, int qtdDado, int tolalFaces) {
        this.tipo = tipo;
        this.qtdVidas = qtdVidas;
        this.poder = poder;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.qtdDado = qtdDado;
        this.tolalFaces = tolalFaces;
    }
}
