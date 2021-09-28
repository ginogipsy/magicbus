package com.ginogipsy.magicbus.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "posizione_magicbus")
@Data
public class PosizioneMagicBus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posizionemagicbus_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "via")
    private String via;

    @Column(name = "civico")
    private String civico;

    @Column(name = "cap")
    private String cap;

    @Column(name = "citta")
    private String citta;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "nazione")
    private String nazione;

}

