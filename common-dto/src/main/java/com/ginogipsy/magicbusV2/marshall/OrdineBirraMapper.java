package com.ginogipsy.magicbusV2.marshall;


import com.ginogipsy.magicbusV2.domain.OrdineBirra;
import com.ginogipsy.magicbusV2.dto.OrdineBirraDTO;
import com.ginogipsy.magicbusV2.repository.OrdineBirraRepository;
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
