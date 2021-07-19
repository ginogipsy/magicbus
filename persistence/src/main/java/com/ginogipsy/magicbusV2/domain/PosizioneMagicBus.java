package com.ginogipsy.magicbusV2.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "posizione_magic_bus")
@Data
public class PosizioneMagicBus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posizionemagicbus_id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "via")
    private String via;

    @Column(name = "civico")
    private Integer civico;

    @Column(name = "cap")
    private Integer cap;

    @Column(name = "citta")
    private String citta;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "nazione")
    private String nazione;

}

