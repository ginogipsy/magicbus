package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "birra")
@Data
@EqualsAndHashCode(exclude = "birrificio", callSuper = false)
public class Birra extends Alcolico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "tipologia_birra")
    @Enumerated(EnumType.STRING)
    private TipologiaBirra tipologiaBirra;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "birrificio_id")
    private Birrificio birrificio;
}
