package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cantina")
@Data
@EqualsAndHashCode(exclude = "vini")
public class Cantina {

    @Id
    @GeneratedValue
    @Column(name = "cantina_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "ubicazione")
    private String ubicazione;

    @Column(name = "nazione")
    private String nazione;

    @OneToMany(mappedBy = "cantina")
    private Set<Vino> vini;
}