package com.ginogipsy.magicbus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ginogipsy.magicbus.domain.enums.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"utenti", "ingredienti"})
@Entity(name = "gusto")
public class Gusto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gusto_id")
    private Integer id;

    @Column(name ="nome", unique = true)
    private String nome;

    //@Lob da implementare per descrizioni grosse
    @Column(name = "descrizione_gusto")
    private String descrizioneGusto;

    @Column(name = "tradizionale", columnDefinition = "TINYINT", length = 1)
    private Boolean tradizionale;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "versione_vegana_disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean versioneVeganaDisponibile;

    @Column(name = "immagine")
    @Lob
    private Byte[] immagine;

    @ManyToMany(mappedBy = "gustiPreferiti", fetch = FetchType.LAZY)
    private Set<User> utenti;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gusto", fetch = FetchType.LAZY)
    private Set<GustoIngrediente> ingredienti;

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

    @ManyToMany(mappedBy = "gustiPreferiti", fetch = FetchType.LAZY)
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private boolean disponibile;

    @Column(name = "gusto_utente", columnDefinition = "TINYINT", length = 1)
    private boolean gustoUtente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userCreator;

}
