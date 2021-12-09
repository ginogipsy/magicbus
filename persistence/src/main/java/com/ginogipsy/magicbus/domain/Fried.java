package com.ginogipsy.magicbus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ginogipsy.magicbus.domain.enums.ProductCategory;
import com.ginogipsy.magicbus.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fritto")
public class Fried {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fritto_id")
    private Integer id;

    @Column(name = "nome")
    private String name;

    //@Lob da implementare per grosse dimensioni
    @Column(name = "descrizione_fritto")
    private String friedDescription;

    @Column(name = "costo")
    private Double cost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fried", fetch = FetchType.EAGER)
    private Set<FriedIngredient> ingredients;

    @Column(name = "immagine")
    @Lob
    private Byte[] image;

    @Column(name = "categoria_prodotto")
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @ManyToMany(mappedBy = "frittiPreferiti", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean available;

    @Column(name = "fritto_utente", columnDefinition = "TINYINT", length = 1)
    private boolean userEntered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userCreator;


}
