package com.apirest.jogorpg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "PERSONAGEM")
@Entity
public class Personagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "QTD_VIDAS")
    private int qtdVidas;
    @Column(name = "PODER")
    private int poder;
    @Column(name = "DEFESA")
    private int defesa;
    @Column(name = "AGILIDADE")
    private int agilidade;
    @Column(name = "QTD_DADOS")
    private int qtdDado;
    @Column(name = "TOTAL_FACES")
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
