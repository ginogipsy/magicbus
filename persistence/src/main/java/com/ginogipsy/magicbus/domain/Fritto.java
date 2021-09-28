package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fritto")
@Data
@EqualsAndHashCode(exclude = {"ingredienti"})
public class Fritto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fritto_id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    //@Lob da implementare per grosse dimensioni
    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fritto")
    private Set<FrittoIngrediente> ingredienti;

    @Column(name = "immagine")
    @Lob
    private Byte[] immagine;

    @Column(name = "tipologia_menu")
    @Enumerated(EnumType.STRING)
    private TipologiaMenu tipologiaMenu;

    @Column(name = "categoria_prodotto")
    @Enumerated(EnumType.STRING)
    private CategoriaProdotto categoriaProdotto;

    @ManyToMany(mappedBy = "frittiPreferiti")
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean disponibile;


}
