package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
    private List<OrdineFritto> ordineFritti;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrdineGusto> ordineGusti;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrdineBibita> ordineBibite;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrdineBirra> ordineBirre;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrdineVino> ordineVini;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordinepagamento_id")
    private OrdinePagamento ordinePagamento;
}

