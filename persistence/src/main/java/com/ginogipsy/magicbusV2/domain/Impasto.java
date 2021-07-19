package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "impasto")
@Data
@EqualsAndHashCode(exclude = {"ingredienti"})
public class Impasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "impasto_id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "impasto_ingrediente",
            joinColumns = @JoinColumn(name = "impasto_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    private Set<Ingrediente> ingredienti;

    @Column(name = "costo_aggiuntivo")
    private Double costoAggiuntivo;

}
