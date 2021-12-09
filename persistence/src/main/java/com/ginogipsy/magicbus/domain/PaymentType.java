package com.ginogipsy.magicbus.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tipologia_pagamento")
@Data
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipologiapagamento_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    @Column(name = "descrizione")
    private String description;
}

