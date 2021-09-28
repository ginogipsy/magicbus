package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.OrdineBirra;
import com.ginogipsy.magicbus.dto.OrdineBirraDTO;
import com.ginogipsy.magicbus.repository.OrdineBirraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineBirraMapper {

    private final ModelMapper modelMapper;
    private final OrdineBirraRepository ordineBirraRepository;

    public OrdineBirraMapper(ModelMapper modelMapper, OrdineBirraRepository ordineBirraRepository) {
        this.modelMapper = modelMapper;
        this.ordineBirraRepository = ordineBirraRepository;
    }

    public OrdineBirra convertToEntity(OrdineBirraDTO ordineBirraDTO){
        return (ordineBirraDTO != null) ? modelMapper.map(ordineBirraDTO, OrdineBirra.class) : null;
    }

    public OrdineBirraDTO convertToDTO(OrdineBirra ordineBirra){
        return (ordineBirra != null) ? modelMapper.map(ordineBirra, OrdineBirraDTO.class) : null;
    }
}
