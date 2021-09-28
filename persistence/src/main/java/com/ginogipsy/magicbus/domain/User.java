package com.ginogipsy.magicbus.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
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

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "numero_cellulare", unique = true, nullable = false)
    private Long numeroCellulare;

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

    @ManyToMany(cascade = CascadeType.PERSIST, fetch= FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_role",
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles;

    @ManyToMany(fetch= FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "gusto_preferito", joinColumns = @JoinColumn(name = "gusto_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Gusto> gustiPreferiti;

    @ManyToMany(fetch= FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "fritto_utente", joinColumns = @JoinColumn(name = "fritto_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Fritto> frittiPreferiti;

    @ManyToMany(fetch= FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "birra_utente", joinColumns = @JoinColumn(name = "birra_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Birra> birrePreferite;

    @ManyToMany(fetch= FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "vino_utente", joinColumns = @JoinColumn(name = "vino_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Vino> viniPreferiti;

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Ordine> ordini;

}
