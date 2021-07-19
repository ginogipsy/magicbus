package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "ordine_pagamento")
@Data
@EqualsAndHashCode(exclude = {"ordine", "tipologiaPagamento"})
public class OrdinePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinepagamento_id")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ordine_fk")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "tipologiapagamento_fk")
    private TipologiaPagamento tipologiaPagamento;

    @Column(name = "pagato")
    private Boolean pagato;

    @Column(name = "ora_pagamento")
    private DateTime oraPagamento;

    @Column(name = "codice_fiscale")
    private String codiceFiscale;
}
