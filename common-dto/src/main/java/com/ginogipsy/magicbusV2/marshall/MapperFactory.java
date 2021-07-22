package com.ginogipsy.magicbusV2.marshall;

import org.springframework.stereotype.Component;

@Component
public class MapperFactory {

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
    private final OrdineMapper ordineMapper;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;
    private final VinoMapper vinoMapper;

    public MapperFactory(BibitaMapper bibitaMapper, BirraMapper birraMapper, BirrificioMapper birrificioMapper, CantinaMapper cantinaMapper, FornitoreMapper fornitoreMapper, FrittoIngredienteMapper frittoIngredienteMapper, FrittoMapper frittoMapper, GustoIngredienteMapper gustoIngredienteMapper, GustoMapper gustoMapper, ImpastoIngredienteMapper impastoIngredienteMapper, ImpastoMapper impastoMapper, IngredienteMapper ingredienteMapper, MarcaProdottoMapper marcaProdottoMapper, OrdineMapper ordineMapper, RoleMapper roleMapper, UserMapper userMapper, VinoMapper vinoMapper) {
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
        this.ordineMapper = ordineMapper;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
        this.vinoMapper = vinoMapper;
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

    public OrdineMapper getOrdineMapper() {
        return ordineMapper;
    }

    public RoleMapper getRoleMapper() {
        return roleMapper;
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
