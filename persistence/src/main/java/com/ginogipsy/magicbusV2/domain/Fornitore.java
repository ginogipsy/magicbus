package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fornitore")
@Data
@EqualsAndHashCode(exclude = {"marche"})
public class Fornitore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fornitore_id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "via")
    private String via;

    @Column(name = "civico")
    private Integer civico;

    @Column(name = "cap")
    private Integer cap;

    @Column(name = "citta")
    private String citta;

    @Column(name = "partita_iva")
    private Integer partitaIva;

    @ManyToMany
    @JoinTable(name = "marca_fornitore", joinColumns = @JoinColumn(name = "marca_id"), inverseJoinColumns = @JoinColumn(name = "fornitore_id"))
    private Set<MarcaProdotto> marche;
}
