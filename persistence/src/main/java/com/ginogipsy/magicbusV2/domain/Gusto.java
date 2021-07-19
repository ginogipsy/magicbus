package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"utenti", "ingredienti"})
@Entity(name = "gusto")
public class Gusto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gusto_id")
    private Long id;

    @Column(name ="nome", unique = true, nullable = false)
    private String nome;

    @Lob
    @Column(name = "descrizione_gusto")
    private String descrizioneGusto;

    @Column(name = "tradizionale")
    private Boolean tradizionale;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "versione_vegana_disponibile")
    private Boolean versioneVeganaDisponibile;


    @ManyToMany(mappedBy = "gustiPreferiti")
    private Set<User> utenti;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gusto")
    private Set<GustoIngrediente> ingredienti;

    @Column(name = "immagine")
    @Lob
    private Byte[] immagine;

    @Column(name = "tipologia_menu")
    @Enumerated(EnumType.STRING)
    private TipologiaMenu tipologiaMenu;

    @Column(name = "base")
    @Enumerated(EnumType.STRING)
    private Base base;

    @Column(name = "periodo_disponibilita")
    @Enumerated(EnumType.STRING)
    private PeriodoDisponibilita periodoDisponibilita;

    @Column(name = "categoria_prodotto")
    @Enumerated(EnumType.STRING)
    private CategoriaProdotto categoriaProdotto;

}
