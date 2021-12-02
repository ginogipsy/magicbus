package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.PeriodoDisponibilita;
import com.ginogipsy.magicbus.domain.enums.TipologiaIngrediente;
import com.ginogipsy.magicbus.domain.enums.UnitaDiMisura;
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
    private UnitaDiMisura unitaDiMisura;

    @ManyToMany(mappedBy = "ingredienti")
    private Set<Allergene> tipoAllergene;

    @Column(name = "tipologia_ingrediente")
    @Enumerated(EnumType.STRING)
    private TipologiaIngrediente tipologiaIngrediente;

    @Column(name = "periodo_disponibilita")
    @Enumerated(EnumType.STRING)
    private PeriodoDisponibilita periodoDisponibilita;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private MarcaProdotto marca;

    @OneToMany(mappedBy = "ingrediente")
    private Set<GustoIngrediente> gusti;

    @OneToMany(mappedBy = "ingrediente")
    private Set<FrittoIngrediente> fritti;

    @OneToMany(mappedBy = "ingrediente")
    private Set<ImpastoIngrediente> impasti;


}
