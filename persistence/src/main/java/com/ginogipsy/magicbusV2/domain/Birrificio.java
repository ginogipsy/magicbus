package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "birrificio")
@Data
@EqualsAndHashCode(exclude = "birrificio")
public class Birrificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birrificio_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "ubicazione")
    private String ubicazione;

    @Column(name = "nazione")
    private String nazione;

    @OneToMany(mappedBy = "birrificio")
    private Set<Birra> birre;
}
