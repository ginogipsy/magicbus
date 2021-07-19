package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ordine")
@Data
@EqualsAndHashCode(exclude = {"orarioDisponibile", "user", "ordineFritti", "ordineGusti", "ordineBibite", "ordinePagamento"})
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordine_id")
    private Long id;

    @Column(name = "ora_inserimento")
    private DateTime oraInserimento;

    @Column(name = "ora_approvazione")
    private DateTime oraApprovazione;

    @ManyToOne
    @JoinColumn(name = "orariodisponibile_id")
    private OrarioDisponibile orarioDisponibile;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrdineFritto> ordineFritti;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrdineGusto> ordineGusti;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrdineBibita> ordineBibite;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordinepagamento_id")
    private OrdinePagamento ordinePagamento;
}

