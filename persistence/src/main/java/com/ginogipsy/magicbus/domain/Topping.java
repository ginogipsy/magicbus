package com.ginogipsy.magicbus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ginogipsy.magicbus.domain.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;

@Getter
@Setter
@Entity(name = "gusto")
public class Topping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gusto_id")
    private Integer id;

    @Column(name ="nome", unique = true)
    private String name;

    //@Lob da implementare per descrizioni grosse
    @Column(name = "descrizione_gusto")
    private String toppingDescription;

    @Column(name = "tradizionale", columnDefinition = "TINYINT", length = 1)
    private Boolean traditional;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "costo")
    private Double cost;

    @Column(name = "versione_vegana_disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean veganOption;

    @Column(name = "immagine")
    @Lob
    private Byte[] image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topping", fetch = FetchType.LAZY)
    private Set<ToppingIngredient> ingredients;

    @Column(name = "base")
    @Enumerated(EnumType.STRING)
    private Base base;

    @Column(name = "periodo_disponibilita")
    @Enumerated(EnumType.STRING)
    private AvailabilityPeriod availabilityPeriod;

    @Column(name = "categoria_prodotto")
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @ManyToMany(mappedBy = "favoriteToppings", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private boolean available;

    @Column(name = "gusto_utente", columnDefinition = "TINYINT", length = 1)
    private boolean userEntered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userCreator;

}
