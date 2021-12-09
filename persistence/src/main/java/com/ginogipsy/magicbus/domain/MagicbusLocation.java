package com.ginogipsy.magicbus.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "posizione_magicbus")
@Data
public class MagicbusLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posizionemagicbus_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    @Column(name = "descrizione")
    private String description;

    @Column(name = "via")
    private String street;

    @Column(name = "civico")
    private String number;

    @Column(name = "cap")
    private String postalCode;

    @Column(name = "citta")
    private String city;

    @Column(name = "provincia")
    private String province;

    @Column(name = "nazione")
    private String country;

}

