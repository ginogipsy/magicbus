package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.Gusto;
import com.ginogipsy.magicbus.dto.GustoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Gusto.class, GustoDTO.class)
                .addMapping(g -> g.getUserCreator().getUsername(), GustoDTO::setUsername);
        return modelMapper;
    }

}
