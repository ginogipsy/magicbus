package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Allergen;
import com.ginogipsy.magicbus.dto.AllergenDTO;
import com.ginogipsy.magicbus.repository.AllergenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AllergenMapper {

    private final ModelMapper modelMapper;
    private final AllergenRepository allergenRepository;

    public AllergenMapper(ModelMapper modelMapper, AllergenRepository allergenRepository) {
        this.modelMapper = modelMapper;
        this.allergenRepository = allergenRepository;
    }

    public Allergen convertToEntity(AllergenDTO allergenDTO){
        return (allergenDTO != null) ? modelMapper.map(allergenDTO, Allergen.class) : null;
    }

    public AllergenDTO convertToDTO(Allergen allergen){
        return (allergen != null) ? modelMapper.map(allergen, AllergenDTO.class) : null;
    }
}