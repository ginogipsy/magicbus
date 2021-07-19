package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "vino")
@Data
@EqualsAndHashCode(exclude = "cantina", callSuper = false)
public class Vino extends Alcolico {

    @Column(name = "qualita_vino")
    @Enumerated(EnumType.STRING)
    private QualitaVino qualitaVino;

    @Column(name = "nome", unique = true)
    private String nome;



    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cantina_id")
    private Cantina cantina;
}

