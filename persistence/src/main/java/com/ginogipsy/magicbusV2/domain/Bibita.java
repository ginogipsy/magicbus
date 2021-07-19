package com.ginogipsy.magicbusV2.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bibita")
public class Bibita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bibita_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "tipologia")
    @Enumerated(EnumType.STRING)
    private TipologiaBibite tipologia;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "costo_al_litro")
    private Double costoAlLitro;

    @Column(name = "formato")
    private Double formato;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private UnitaDiMisura unitaDiMisura;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "tipologia_menu")
    @Enumerated(EnumType.STRING)
    private TipologiaMenu tipologiaMenu;
}
