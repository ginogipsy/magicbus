package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.Taste;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.TasteDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Taste.class, TasteDTO.class)
                .addMapping(g -> g.getUserCreator().getUsername(), TasteDTO::setUsername)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new AppreciatedUsersListToCountConverter()).map(source.getUsers(), destination.getAppreciations());
                    }
                });

        modelMapper.createTypeMap(Fried.class, FriedDTO.class)
                .addMapping(f -> f.getUserCreator().getUsername(), FriedDTO::setUsername)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new AppreciatedUsersListToCountConverter()).map(source.getUsers(), destination.getAppreciations());
                    }
                });


        return modelMapper;
    }
}
