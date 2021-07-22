package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"fornitore", "descrizione"})
@Table(name = "marca_prodotto")
public class MarcaProdotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marcaprodotto_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToMany(mappedBy = "marche")
    private Set<Fornitore> fornitore;

    @OneToMany(mappedBy = "marca")
    private Set<Ingrediente> ingredienti;



}
