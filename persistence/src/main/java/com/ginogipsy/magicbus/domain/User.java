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
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "numero_cellulare", unique = true, nullable = false)
    private String numeroCellulare;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "civico")
    private String civico;

    @Column(name = "citta")
    private String citta;

    @Column(name = "cap")
    private String cap;

    @Column(name = "codice_fiscale", unique = true)
    private String codiceFiscale;

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
    private Set<Taste> gustiPreferiti;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fritto_preferito", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "fritto_id"))
    @JsonIgnore
    private Set<Fried> frittiPreferiti;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "birra_utente", joinColumns = @JoinColumn(name = "birra_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<Beer> birrePreferite;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vino_utente", joinColumns = @JoinColumn(name = "vino_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<Wine> viniPreferiti;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Order> ordini;

    @OneToMany(mappedBy = "userCreator")
    @JsonIgnore
    private Set<Taste> gustiInseriti;

}

