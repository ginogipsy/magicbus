package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.FriedOrder;
import com.ginogipsy.magicbus.dto.OrdineFrittoDTO;
import com.ginogipsy.magicbus.repository.OrdineFrittoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineFrittoMapper {

    private final ModelMapper modelMapper;
    private final OrdineFrittoRepository ordineFrittoRepository;

    public OrdineFrittoMapper(ModelMapper modelMapper, OrdineFrittoRepository ordineFrittoRepository) {
        this.modelMapper = modelMapper;
        this.ordineFrittoRepository = ordineFrittoRepository;
    }

    public FriedOrder convertToEntity(OrdineFrittoDTO ordineFrittoDTO){
        return (ordineFrittoDTO != null) ? modelMapper.map(ordineFrittoDTO, FriedOrder.class) : null;
    }

    public OrdineFrittoDTO convertToDTO(FriedOrder friedOrder){
        return (friedOrder != null) ? modelMapper.map(friedOrder, OrdineFrittoDTO.class) : null;
    }
}
