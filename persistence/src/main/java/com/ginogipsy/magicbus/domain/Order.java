package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@EqualsAndHashCode(exclude = {"orarioDisponibile", "user", "ordineFritti", "ordineGusti", "ordineBibite", "ordinePagamento"})
@Entity(name = "ordine")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordine_id")
    private Integer id;

    @Column(name = "ora_inserimento")
    private Date insertionTime;

    @Column(name = "ora_approvazione")
    private Date approvalTime;

    @ManyToOne
    @JoinColumn(name = "orariodisponibile_id")
    private AvailableTime availableTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FriedOrder> friedOrders;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TasteOrder> tastesOrders;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DrinkOrder> drinkOrders;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BeerOrder> beerOrders;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WineOrder> wineOrders;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ordinepagamento_id")
    private PaymentOrder payment;
}

