package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.AvailabilityPeriod;
import com.ginogipsy.magicbus.domain.enums.IngredientType;
import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingrediente")
@Data
@EqualsAndHashCode(exclude = {"tipoAllergene", "marca", "gusti", "fritti", "impasti"})
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingrediente_id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean disponibile;

    @Column(name = "costo_aggiunta_cliente")
    private Double costoAggiuntaCliente;

    @Column(name = "costo_acquisto")
    private Double costoDiAcquisto;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;

    @ManyToMany(mappedBy = "ingredienti", fetch = FetchType.LAZY)
    private Set<Allergen> tipoAllergen;

    @Column(name = "tipologia_ingrediente")
    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    @Column(name = "periodo_disponibilita")
    @Enumerated(EnumType.STRING)
    private AvailabilityPeriod availabilityPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    private MarcaProdotto marca;

    @OneToMany(mappedBy = "ingrediente",fetch = FetchType.LAZY)
    private Set<GustoIngrediente> gusti;

    @OneToMany(mappedBy = "ingrediente", fetch = FetchType.LAZY)
    private Set<FrittoIngrediente> fritti;

    @OneToMany(mappedBy = "ingrediente", fetch = FetchType.LAZY)
    private Set<ImpastoIngrediente> impasti;


}
