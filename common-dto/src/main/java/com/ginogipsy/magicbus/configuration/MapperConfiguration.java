package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.dto.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ginogipsy
 */

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Topping.class, ToppingDTO.class)
                .addMapping(g -> g.getUserCreator().getUsername(), ToppingDTO::setUsername)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new AppreciatedUsersListToCountConverter()).map(source.getUsers(), destination.getAppreciations());
                        using(new ToppingIngredientListToIngredientNameListConverter()).map(source.getIngredients(), destination.getIngredients());
                    }
                });

        modelMapper.createTypeMap(Fried.class, FriedDTO.class)
                .addMapping(f -> f.getUserCreator().getUsername(), FriedDTO::setUsername)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new AppreciatedUsersListToCountConverter()).map(source.getUsers(), destination.getAppreciations());
                        using(new FriedIngredientListToIngredientNameListConverter()).map(source.getIngredients(), destination.getIngredients());
                    }
                });

        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new InsertedToppingListToNameList()).map(source.getToppingsInserted(), destination.getToppingsInserted());
                    }
                });

        modelMapper.createTypeMap(Dough.class, DoughDTO.class)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new DoughIngredientListToIngredientNameListConverter()).map(source.getIngredients(), destination.getIngredients());
                    }
                });

        modelMapper.createTypeMap(Brand.class, BrandDTO.class)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new SuppliersToSuppliersNameConverter()).map(source.getSuppliers(), destination.getSuppliers());
                        using(new IngredientToIngredientNameConverter()).map(source.getIngredients(), destination.getIngredients());
                    }
                });


        modelMapper.createTypeMap(Ingredient.class, IngredientDTO.class)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new DoughIngredientListToDoughNameListConverter()).map(source.getDoughs(), destination.getDoughs());
                        using(new FriedIngredientListToFriedNameListConverter()).map(source.getFried(), destination.getFried());
                        using(new ToppingIngredientListToToppingNameListConverter()).map(source.getToppings(), destination.getToppings());
                    }
                });

        return modelMapper;
    }
}
