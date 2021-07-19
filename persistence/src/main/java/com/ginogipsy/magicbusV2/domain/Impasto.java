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

    @Column(name = "costo_aggiuntivo")
    private Double costoAggiuntivo;

    @OneToMany(mappedBy = "impasto", cascade = CascadeType.ALL)
    private Set<ImpastoIngrediente> ingredienti;

}
