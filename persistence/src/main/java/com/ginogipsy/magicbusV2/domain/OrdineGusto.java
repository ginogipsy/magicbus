package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "ordine_gusto")
@Data
@EqualsAndHashCode(exclude = {"ordine", "gusto", "impasto"})
public class OrdineGusto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinegusto_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "gusto_id")
    private Gusto gusto;

    @ManyToOne
    @JoinColumn(name = "impasto_id")
    private Impasto impasto;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "annullato")
    private Boolean annullato;

    @Column(name = "costo_totale")
    private Double costoTotale;

    public void setCostoTotale(){
        this.costoTotale = this.quantita * this.gusto.getCosto();
    }

    public void setImpasto(Impasto impasto){
        if(gusto.getCategoriaProdotto().equals(CategoriaProdotto.PIZZA) || gusto.getCategoriaProdotto().equals(CategoriaProdotto.CALZONE) || gusto.getCategoriaProdotto().equals(CategoriaProdotto.PANINO)){
            this.impasto = impasto;
            this.costoTotale = costoTotale + impasto.getCostoAggiuntivo()*this.quantita;
        }
    }
}

