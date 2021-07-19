package com.ginogipsy.magicbusV2.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tipologia_pagamento")
@Data
public class TipologiaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipologiapagamento_id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;
}

