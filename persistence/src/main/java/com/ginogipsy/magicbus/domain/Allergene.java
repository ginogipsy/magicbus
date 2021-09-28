package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;


@Data
@EqualsAndHashCode(exclude = {"ingredienti"})
@Entity(name = "allergene")
public class Allergene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergene_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToMany
    @JoinTable(name = "ingrediente_allergene",
            joinColumns = @JoinColumn(name = "ingrediente_id"),
            inverseJoinColumns = @JoinColumn(name = "allergene_id"))
    private Set<Ingrediente> ingredienti;
}
