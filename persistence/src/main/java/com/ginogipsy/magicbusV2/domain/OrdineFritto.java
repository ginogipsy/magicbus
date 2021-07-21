package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "ordine_fritto")
@Data
@EqualsAndHashCode(exclude = {"ordine", "fritto"})
public class OrdineFritto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinefritto_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "fritto_id")
    private Fritto fritto;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "annullato", columnDefinition = "TINYINT", length = 1)
    private Boolean annullato;

    @Column(name = "costo_totale")
    private Double costoTotale;

    public void setCostoTotale(){
        this.costoTotale = this.quantita * this.fritto.getCosto();
    }
}

