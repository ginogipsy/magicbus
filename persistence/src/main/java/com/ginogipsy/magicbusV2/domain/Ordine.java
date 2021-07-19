package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Data
@EqualsAndHashCode(exclude = {"orarioDisponibile", "user", "ordineFritti", "ordineGusti", "ordineBibite", "ordinePagamento"})
@Entity(name = "ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordine_id")
    private Integer id;

    @Column(name = "ora_inserimento")
    private Date oraInserimento;

    @Column(name = "ora_approvazione")
    private Date oraApprovazione;

    @ManyToOne
    @JoinColumn(name = "orariodisponibile_id")
    private OrarioDisponibile orarioDisponibile;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private Set<OrdineFritto> ordineFritti;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private Set<OrdineGusto> ordineGusti;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private Set<OrdineBibita> ordineBibite;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private Set<OrdineBirra> ordineBirre;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private Set<OrdineVino> ordineVini;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordinepagamento_id")
    private OrdinePagamento ordinePagamento;
}

