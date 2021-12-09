package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.Taste;
import com.ginogipsy.magicbus.dto.FrittoDTO;
import com.ginogipsy.magicbus.dto.GustoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Taste.class, GustoDTO.class)
                .addMapping(g -> g.getUserCreator().getUsername(), GustoDTO::setUsername)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new AppreciatedUsersListToCountConverter()).map(source.getUsers(), destination.getAppreciations());
                    }
                });

        modelMapper.createTypeMap(Fried.class, FrittoDTO.class)
                .addMapping(f -> f.getUserCreator().getUsername(), FrittoDTO::setUsername)
                .addMappings(new PropertyMap<>() {
                    @Override
                    protected void configure() {
                        using(new AppreciatedUsersListToCountConverter()).map(source.getUsers(), destination.getAppreciations());
                    }
                });


        return modelMapper;
    }
}
