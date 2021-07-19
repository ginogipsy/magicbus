package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "ordine_bibita")
@Data
@EqualsAndHashCode(exclude = {"ordine", "bibita"})
public class OrdineBibita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinebibita_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "bibita_id")
    private Bibita bibita;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "annullato")
    private Boolean annullato;

    @Column(name = "costo_totale")
    private Double costoTotale;

    public void setCostoTotale(){
        this.costoTotale = this.quantita * this.bibita.getCosto();
    }

}

