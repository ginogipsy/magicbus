package com.ginogipsy.magicbus.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;

@Getter
@Setter
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nome")
    private String name;

    @Column(name = "cognome")
    private String surname;

    @Column(name = "numero_cellulare", unique = true, nullable = false)
    private String cellNumber;

    @Column(name = "indirizzo")
    private String street;

    @Column(name = "civico")
    private String number;

    @Column(name = "citta")
    private String city;

    @Column(name = "cap")
    private String postalCode;

    @Column(name = "codice_fiscale", unique = true)
    private String fiscalCode;

    @Column(name = "is_enabled", columnDefinition = "TINYINT", length = 1)
    private Boolean isEnabled;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_role",
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            joinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<Role> roles;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gusto_preferito", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "gusto_id"))
    private Set<Taste> favoriteTastes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fritto_preferito", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "fritto_id"))
    @JsonIgnore
    private Set<Fried> favoriteFried;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "birra_utente", joinColumns = @JoinColumn(name = "birra_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<Beer> favoriteBeers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vino_utente", joinColumns = @JoinColumn(name = "vino_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<Wine> favoriteWines;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Order> orders;

    @OneToMany(mappedBy = "userCreator")
    @JsonIgnore
    private Set<Taste> tastesInserted;

}

