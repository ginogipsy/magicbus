package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"ordine", "vino"})
@Entity(name = "ordine_vino")
public class OrdineVino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinevino_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "vino_id")
    private Vino vino;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "annullato", columnDefinition = "TINYINT", length = 1)
    private Boolean annullato;

    @Column(name = "costo_totale")
    private Double costoTotale;

    public void setCostoTotale(){
        this.costoTotale = this.quantita * this.vino.getCosto();
    }
}
