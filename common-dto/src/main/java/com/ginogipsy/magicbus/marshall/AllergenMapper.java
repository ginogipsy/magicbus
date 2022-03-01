package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Allergen;
import com.ginogipsy.magicbus.dto.AllergenDTO;
import com.ginogipsy.magicbus.repository.AllergenRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    public AllergenDTO convertToDTO(final Allergen allergen) {
        return Optional.ofNullable(allergen)
                .map(a -> modelMapper.map(a, AllergenDTO.class))
                .orElse(null);
    }

    public AllergenDTO findByName(final String name) {
        log.info("Searching allergen where the name is " + name + "..");
        return Optional.ofNullable(name)
                .map(allergenRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public AllergenDTO save(final AllergenDTO allergenDTO) {
        log.info("Saving allergen on db..");
        return Optional.ofNullable(allergenDTO)
                .map(this::convertToEntity)
                .map(allergenRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<AllergenDTO> findAll() {
        return allergenRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}
