package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;


@Data
@EqualsAndHashCode(exclude = "birrificio", callSuper = false)
@Entity(name = "birra")
public class Birra{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birra_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String nome;

    //@Lob
    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "grado_alcolico")
    private Double gradoAlcolico;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "tipologia_birra")
    @Enumerated(EnumType.STRING)
    private TipologiaBirra tipologiaBirra;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "birrificio_id")
    private Birrificio birrificio;

    @ManyToMany(mappedBy = "birrePreferite")
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean disponibile;
}
