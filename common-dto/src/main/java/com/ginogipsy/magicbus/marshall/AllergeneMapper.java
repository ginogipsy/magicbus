package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Allergen;
import com.ginogipsy.magicbus.dto.AllergeneDTO;
import com.ginogipsy.magicbus.repository.AllergenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AllergeneMapper {

    private final ModelMapper modelMapper;
    private final AllergenRepository allergenRepository;

    public AllergeneMapper(ModelMapper modelMapper, AllergenRepository allergenRepository) {
        this.modelMapper = modelMapper;
        this.allergenRepository = allergenRepository;
    }

    public Allergen convertToEntity(AllergeneDTO allergeneDTO){
        return (allergeneDTO != null) ? modelMapper.map(allergeneDTO, Allergen.class) : null;
    }

    public AllergeneDTO convertToDTO(Allergen allergen){
        return (allergen != null) ? modelMapper.map(allergen, AllergeneDTO.class) : null;
    }
}
