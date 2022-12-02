package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.WineQualityEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * @author ginogipsy
 */
@Data
@EqualsAndHashCode(exclude = "cantina", callSuper = false)
@Entity(name = "vino")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vino_id")
    private Integer id;

    @Column(name = "qualita_vino")
    @Enumerated(EnumType.STRING)
    private WineQualityEnum wineQualityEnum;

    @Column(name = "nome", unique = true)
    private String name;

    //@Lob(per grosse quantita' di stringhe, da implementare
    @Column(name = "descrizione")
    private String description;

    @Column(name = "grado_alcolico")
    private Double alcoholicContent;

    @Column(name = "costo")
    private Double cost;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cantina_id")
    private Winery winery;

    @ManyToMany(mappedBy = "favoriteWines", fetch = FetchType.LAZY)
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean available;



}

