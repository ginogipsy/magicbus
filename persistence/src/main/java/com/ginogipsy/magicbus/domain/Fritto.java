package com.ginogipsy.magicbus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ginogipsy.magicbus.domain.enums.CategoriaProdotto;
import com.ginogipsy.magicbus.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fritto")
public class Fritto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fritto_id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    //@Lob da implementare per grosse dimensioni
    @Column(name = "descrizione_fritto")
    private String descrizioneFritto;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fritto", fetch = FetchType.EAGER)
    private Set<FrittoIngrediente> ingredienti;

    @Column(name = "immagine")
    @Lob
    private Byte[] immagine;

    @Column(name = "categoria_prodotto")
    @Enumerated(EnumType.STRING)
    private CategoriaProdotto categoriaProdotto;

    @ManyToMany(mappedBy = "frittiPreferiti")
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean disponibile;

    @Column(name = "fritto_utente", columnDefinition = "TINYINT", length = 1)
    private boolean frittoUtente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userCreator;


}
