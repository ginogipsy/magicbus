package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.WineQuality;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;


@Data
@EqualsAndHashCode(exclude = "cantina", callSuper = false)
@Entity(name = "vino")
public class Vino{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vino_id")
    private Integer id;

    @Column(name = "qualita_vino")
    @Enumerated(EnumType.STRING)
    private WineQuality wineQuality;

    @Column(name = "nome", unique = true)
    private String nome;

    //@Lob(per grosse quantita' di stringhe, da implementare
    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "grado_alcolico")
    private Double gradoAlcolico;

    @Column(name = "costo")
    private Double costo;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cantina_id")
    private Cantina cantina;

    @ManyToMany(mappedBy = "viniPreferiti", fetch = FetchType.LAZY)
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean disponibile;



}

