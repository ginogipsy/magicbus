package com.ginogipsy.magicbus.marshall;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author ginogipsy
 */

@Component
@RequiredArgsConstructor
public class MapperFactory {

    private final AllergenMapper allergenMapper;
    private final DrinkMapper drinkMapper;
    private final BeerMapper beerMapper;
    private final BreweryMapper breweryMapper;
    private final WineryMapper wineryMapper;
    private final SupplierMapper supplierMapper;
    private final FriedIngredientMapper friedIngredientMapper;
    private final FriedMapper friedMapper;
    private final ToppingIngredientMapper toppingIngredientMapper;
    private final ToppingMapper toppingMapper;
    private final DoughIngredientMapper doughIngredientMapper;
    private final DoughMapper doughMapper;
    private final IngredientMapper ingredientMapper;
    private final BrandMapper brandMapper;
    private final AvailableTimeMapper availableTimeMapper;
    private final DrinkOrderMapper drinkOrderMapper;
    private final BeerOrderMapper beerOrderMapper;
    private final FriedOrderMapper friedOrderMapper;
    private final ToppingOrderMapper toppingOrderMapper;
    private final OrderMapper orderMapper;
    private final WineOrderMapper wineOrderMapper;
    private final MagicbusLocationMapper magicbusLocationMapper;
    private final RoleMapper roleMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final UserMapper userMapper;
    private final WineMapper wineMapper;
    private final RefreshTokenMapper refreshTokenMapper;

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

    public ToppingIngredientMapper getToppingIngredientMapper() {
        return toppingIngredientMapper;
    }

    public ToppingMapper getToppingMapper() {
        return toppingMapper;
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

    public ToppingOrderMapper getTasteOrderMapper() {
        return toppingOrderMapper;
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
