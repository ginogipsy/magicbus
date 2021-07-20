package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"gustiPreferiti","frittiPreferiti","gustiPreferiti","bibitePreferite", "ordini" })
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
    private Integer civico;

    @Column(name = "citta")
    private String citta;

    @Column(name = "cap")
    private Integer cap;

    @Column(name = "codice_fiscale", unique = true)
    private String codiceFiscale;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name = "gusto_preferito", joinColumns = @JoinColumn(name = "gusto_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Gusto> gustiPreferiti;

    @ManyToMany
    @JoinTable(name = "fritto_utente", joinColumns = @JoinColumn(name = "fritto_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Fritto> frittiPreferiti;

    @ManyToMany
    @JoinTable(name = "birra_utente", joinColumns = @JoinColumn(name = "birra_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Birra> birrePreferite;

    @ManyToMany
    @JoinTable(name = "vino_utente", joinColumns = @JoinColumn(name = "vino_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Vino> viniPreferiti;

    @OneToMany(mappedBy = "user")
    private Set<Ordine> ordini;

}

