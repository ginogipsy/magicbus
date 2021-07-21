package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"ordine", "birra"})
@Entity(name = "ordine_birra")
public class OrdineBirra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinebirra_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "birra_id")
    private Birra birra;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "annullato", columnDefinition = "TINYINT", length = 1)
    private Boolean annullato;

    @Column(name = "costo_totale")
    private Double costoTotale;

    public void setCostoTotale(){
        this.costoTotale = this.quantita * this.birra.getCosto();
    }
}
