package com.ginogipsy.magicbus.marshall;

import org.springframework.stereotype.Component;

@Component
public class MapperFactory {

    private final AllergeneMapper allergeneMapper;
    private final BibitaMapper bibitaMapper;
    private final BirraMapper birraMapper;
    private final BirrificioMapper birrificioMapper;
    private final CantinaMapper cantinaMapper;
    private final FornitoreMapper fornitoreMapper;
    private final FrittoIngredienteMapper frittoIngredienteMapper;
    private final FrittoMapper frittoMapper;
    private final GustoIngredienteMapper gustoIngredienteMapper;
    private final GustoMapper gustoMapper;
    private final ImpastoIngredienteMapper impastoIngredienteMapper;
    private final ImpastoMapper impastoMapper;
    private final IngredienteMapper ingredienteMapper;
    private final MarcaProdottoMapper marcaProdottoMapper;
    private final OrarioDisponibileMapper orarioDisponibileMapper;
    private final OrdineBibitaMapper ordineBibitaMapper;
    private final OrdineBirraMapper ordineBirraMapper;
    private final OrdineFrittoMapper ordineFrittoMapper;
    private final OrdineGustoMapper ordineGustoMapper;
    private final OrdineMapper ordineMapper;
    private final OrdineVinoMapper ordineVinoMapper;
    private final PosizioneMagicBusMapper posizioneMagicBusMapper;
    private final RoleMapper roleMapper;
    private final TipologiaPagamentoMapper tipologiaPagamentoMapper;
    private final UserMapper userMapper;
    private final VinoMapper vinoMapper;
    private final RefreshTokenMapper refreshTokenMapper;

    public MapperFactory(AllergeneMapper allergeneMapper, BibitaMapper bibitaMapper, BirraMapper birraMapper, BirrificioMapper birrificioMapper, CantinaMapper cantinaMapper, FornitoreMapper fornitoreMapper, FrittoIngredienteMapper frittoIngredienteMapper, FrittoMapper frittoMapper, GustoIngredienteMapper gustoIngredienteMapper, GustoMapper gustoMapper, ImpastoIngredienteMapper impastoIngredienteMapper, ImpastoMapper impastoMapper, IngredienteMapper ingredienteMapper, MarcaProdottoMapper marcaProdottoMapper, OrarioDisponibileMapper orarioDisponibileMapper, OrdineBibitaMapper ordineBibitaMapper, OrdineBirraMapper ordineBirraMapper, OrdineFrittoMapper ordineFrittoMapper, OrdineGustoMapper ordineGustoMapper, OrdineMapper ordineMapper, OrdineVinoMapper ordineVinoMapper, PosizioneMagicBusMapper posizioneMagicBusMapper, RoleMapper roleMapper, TipologiaPagamentoMapper tipologiaPagamentoMapper, UserMapper userMapper, VinoMapper vinoMapper, RefreshTokenMapper refreshTokenMapper) {
        this.allergeneMapper = allergeneMapper;
        this.bibitaMapper = bibitaMapper;
        this.birraMapper = birraMapper;
        this.birrificioMapper = birrificioMapper;
        this.cantinaMapper = cantinaMapper;
        this.fornitoreMapper = fornitoreMapper;
        this.frittoIngredienteMapper = frittoIngredienteMapper;
        this.frittoMapper = frittoMapper;
        this.gustoIngredienteMapper = gustoIngredienteMapper;
        this.gustoMapper = gustoMapper;
        this.impastoIngredienteMapper = impastoIngredienteMapper;
        this.impastoMapper = impastoMapper;
        this.ingredienteMapper = ingredienteMapper;
        this.marcaProdottoMapper = marcaProdottoMapper;
        this.orarioDisponibileMapper = orarioDisponibileMapper;
        this.ordineBibitaMapper = ordineBibitaMapper;
        this.ordineBirraMapper = ordineBirraMapper;
        this.ordineFrittoMapper = ordineFrittoMapper;
        this.ordineGustoMapper = ordineGustoMapper;
        this.ordineMapper = ordineMapper;
        this.ordineVinoMapper = ordineVinoMapper;
        this.posizioneMagicBusMapper = posizioneMagicBusMapper;
        this.roleMapper = roleMapper;
        this.tipologiaPagamentoMapper = tipologiaPagamentoMapper;
        this.userMapper = userMapper;
        this.vinoMapper = vinoMapper;
        this.refreshTokenMapper = refreshTokenMapper;
    }

    public AllergeneMapper getAllergeneMapper() {
        return allergeneMapper;
    }

    public BibitaMapper getBibitaMapper() {
        return bibitaMapper;
    }

    public BirraMapper getBirraMapper() {
        return birraMapper;
    }

    public BirrificioMapper getBirrificioMapper() {
        return birrificioMapper;
    }

    public CantinaMapper getCantinaMapper() {
        return cantinaMapper;
    }

    public FornitoreMapper getFornitoreMapper() {
        return fornitoreMapper;
    }

    public FrittoMapper getFrittoMapper() {
        return frittoMapper;
    }

    public GustoIngredienteMapper getGustoIngredienteMapper() {
        return gustoIngredienteMapper;
    }

    public GustoMapper getGustoMapper() {
        return gustoMapper;
    }

    public RefreshTokenMapper getRefreshTokenMapper() {
        return refreshTokenMapper;
    }

    public ImpastoIngredienteMapper getImpastoIngredienteMapper() {
        return impastoIngredienteMapper;
    }

    public ImpastoMapper getImpastoMapper() {
        return impastoMapper;
    }

    public IngredienteMapper getIngredienteMapper() {
        return ingredienteMapper;
    }

    public MarcaProdottoMapper getMarcaProdottoMapper() {
        return marcaProdottoMapper;
    }

    public OrarioDisponibileMapper getOrarioDisponibileMapper() {
        return orarioDisponibileMapper;
    }

    public OrdineBibitaMapper getOrdineBibitaMapper() {
        return ordineBibitaMapper;
    }

    public OrdineBirraMapper getOrdineBirraMapper() {
        return ordineBirraMapper;
    }

    public OrdineFrittoMapper getOrdineFrittoMapper() {
        return ordineFrittoMapper;
    }

    public OrdineGustoMapper getOrdineGustoMapper() {
        return ordineGustoMapper;
    }

    public OrdineMapper getOrdineMapper() {
        return ordineMapper;
    }

    public OrdineVinoMapper getOrdineVinoMapper() {
        return ordineVinoMapper;
    }

    public PosizioneMagicBusMapper getPosizioneMagicBusMapper() {
        return posizioneMagicBusMapper;
    }

    public RoleMapper getRoleMapper() {
        return roleMapper;
    }

    public TipologiaPagamentoMapper getTipologiaPagamentoMapper() {
        return tipologiaPagamentoMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public VinoMapper getVinoMapper() {
        return vinoMapper;
    }

    public FrittoIngredienteMapper getFrittoIngredienteMapper() {
        return frittoIngredienteMapper;
    }

}
