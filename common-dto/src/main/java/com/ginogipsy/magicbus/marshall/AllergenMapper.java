package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Allergen;
import com.ginogipsy.magicbus.dto.AllergenDTO;
import com.ginogipsy.magicbus.repository.AllergenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AllergenMapper {

    private final ModelMapper modelMapper;
    private final AllergenRepository allergenRepository;

    public AllergenMapper(ModelMapper modelMapper, AllergenRepository allergenRepository) {
        this.modelMapper = modelMapper;
        this.allergenRepository = allergenRepository;
    }

    public Allergen convertToEntity(final AllergenDTO allergenDTO){
        return Optional.ofNullable(allergenDTO)
                .map(a -> modelMapper.map(a, Allergen.class))
                .orElse(null);
    }

    public AllergenDTO convertToDTO(final Allergen allergen){
        return Optional.ofNullable(allergen)
                .map(a -> modelMapper.map(a, AllergenDTO.class))
                .orElse(null);
    }
}
