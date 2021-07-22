package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Allergene;
import com.ginogipsy.magicbusV2.dto.AllergeneDTO;
import com.ginogipsy.magicbusV2.repository.AllergeneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AllergeneMapper {

    private final ModelMapper modelMapper;
    private final AllergeneRepository allergeneRepository;

    public AllergeneMapper(ModelMapper modelMapper, AllergeneRepository allergeneRepository) {
        this.modelMapper = modelMapper;
        this.allergeneRepository = allergeneRepository;
    }

    public Allergene convertToEntity(AllergeneDTO allergeneDTO){
        return (allergeneDTO != null) ? modelMapper.map(allergeneDTO, Allergene.class) : null;
    }

    public AllergeneDTO convertToDTO(Allergene allergene){
        return (allergene != null) ? modelMapper.map(allergene, AllergeneDTO.class) : null;
    }
}
