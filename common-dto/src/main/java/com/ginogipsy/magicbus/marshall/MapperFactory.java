package com.ginogipsy.magicbus.marshall;

import org.springframework.stereotype.Component;

@Component
public class MapperFactory {

    private final AllergenMapper allergenMapper;
    private final DrinkMapper drinkMapper;
    private final BeerMapper beerMapper;
    private final BreweryMapper breweryMapper;
    private final WineryMapper wineryMapper;
    private final SupplierMapper supplierMapper;
    private final FriedIngredientMapper friedIngredientMapper;
    private final FriedMapper friedMapper;
    private final TasteIngredientMapper tasteIngredientMapper;
    private final TasteMapper tasteMapper;
    private final DoughIngredientMapper doughIngredientMapper;
    private final DoughMapper doughMapper;
    private final IngredientMapper ingredientMapper;
    private final BrandMapper brandMapper;
    private final AvailableTimeMapper availableTimeMapper;
    private final DrinkOrderMapper drinkOrderMapper;
    private final BeerOrderMapper beerOrderMapper;
    private final FriedOrderMapper friedOrderMapper;
    private final TasteOrderMapper tasteOrderMapper;
    private final OrderMapper orderMapper;
    private final WineOrderMapper wineOrderMapper;
    private final MagicbusLocationMapper magicbusLocationMapper;
    private final RoleMapper roleMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final UserMapper userMapper;
    private final WineMapper wineMapper;
    private final RefreshTokenMapper refreshTokenMapper;

    public MapperFactory(AllergenMapper allergenMapper, DrinkMapper drinkMapper, BeerMapper beerMapper, BreweryMapper breweryMapper, WineryMapper wineryMapper, SupplierMapper supplierMapper, FriedIngredientMapper friedIngredientMapper, FriedMapper friedMapper, TasteIngredientMapper tasteIngredientMapper, TasteMapper tasteMapper, DoughIngredientMapper doughIngredientMapper, DoughMapper doughMapper, IngredientMapper ingredientMapper, BrandMapper brandMapper, AvailableTimeMapper availableTimeMapper, DrinkOrderMapper drinkOrderMapper, BeerOrderMapper beerOrderMapper, FriedOrderMapper friedOrderMapper, TasteOrderMapper tasteOrderMapper, OrderMapper orderMapper, WineOrderMapper wineOrderMapper, MagicbusLocationMapper magicbusLocationMapper, RoleMapper roleMapper, PaymentTypeMapper paymentTypeMapper, UserMapper userMapper, WineMapper wineMapper, RefreshTokenMapper refreshTokenMapper) {
        this.allergenMapper = allergenMapper;
        this.drinkMapper = drinkMapper;
        this.beerMapper = beerMapper;
        this.breweryMapper = breweryMapper;
        this.wineryMapper = wineryMapper;
        this.supplierMapper = supplierMapper;
        this.friedIngredientMapper = friedIngredientMapper;
        this.friedMapper = friedMapper;
        this.tasteIngredientMapper = tasteIngredientMapper;
        this.tasteMapper = tasteMapper;
        this.doughIngredientMapper = doughIngredientMapper;
        this.doughMapper = doughMapper;
        this.ingredientMapper = ingredientMapper;
        this.brandMapper = brandMapper;
        this.availableTimeMapper = availableTimeMapper;
        this.drinkOrderMapper = drinkOrderMapper;
        this.beerOrderMapper = beerOrderMapper;
        this.friedOrderMapper = friedOrderMapper;
        this.tasteOrderMapper = tasteOrderMapper;
        this.orderMapper = orderMapper;
        this.wineOrderMapper = wineOrderMapper;
        this.magicbusLocationMapper = magicbusLocationMapper;
        this.roleMapper = roleMapper;
        this.paymentTypeMapper = paymentTypeMapper;
        this.userMapper = userMapper;
        this.wineMapper = wineMapper;
        this.refreshTokenMapper = refreshTokenMapper;
    }

    public AllergenMapper getAllergenMapper() {
        return allergenMapper;
    }

    public DrinkMapper getDrinkMapper() {
        return drinkMapper;
    }

    public BeerMapper getBeerMapper() {
        return beerMapper;
    }

    public BreweryMapper getBreweryMapper() {
        return breweryMapper;
    }

    public WineryMapper getWineryMapper() {
        return wineryMapper;
    }

    public SupplierMapper getSupplierMapper() {
        return supplierMapper;
    }

    public FriedMapper getFriedMapper() {
        return friedMapper;
    }

    public TasteIngredientMapper getTasteIngredientMapper() {
        return tasteIngredientMapper;
    }

    public TasteMapper getTasteMapper() {
        return tasteMapper;
    }

    public RefreshTokenMapper getRefreshTokenMapper() {
        return refreshTokenMapper;
    }

    public DoughIngredientMapper getDoughIngredientMapper() {
        return doughIngredientMapper;
    }

    public DoughMapper getDoughMapper() {
        return doughMapper;
    }

    public IngredientMapper getIngredientMapper() {
        return ingredientMapper;
    }

    public BrandMapper getBrandMapper() {
        return brandMapper;
    }

    public AvailableTimeMapper getAvailableTimeMapper() {
        return availableTimeMapper;
    }

    public DrinkOrderMapper getDrinkOrderMapper() {
        return drinkOrderMapper;
    }

    public BeerOrderMapper getBeerOrderMapper() {
        return beerOrderMapper;
    }

    public FriedOrderMapper getFriedOrderMapper() {
        return friedOrderMapper;
    }

    public TasteOrderMapper getTasteOrderMapper() {
        return tasteOrderMapper;
    }

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    public WineOrderMapper getWineOrderMapper() {
        return wineOrderMapper;
    }

    public MagicbusLocationMapper getMagicbusLocationMapper() {
        return magicbusLocationMapper;
    }

    public RoleMapper getRoleMapper() {
        return roleMapper;
    }

    public PaymentTypeMapper getPaymentTypeMapper() {
        return paymentTypeMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public WineMapper getWineMapper() {
        return wineMapper;
    }

    public FriedIngredientMapper getFriedIngredientMapper() {
        return friedIngredientMapper;
    }

}
