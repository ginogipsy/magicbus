package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Allergen;
import com.ginogipsy.magicbus.dto.AllergenDTO;
import com.ginogipsy.magicbus.repository.AllergenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class AllergenMapper {

    private final ModelMapper modelMapper;
    private final AllergenRepository allergenRepository;

    public Optional<Allergen> convertToEntity(final Optional<AllergenDTO> allergenDTO){
        return Optional.ofNullable(allergenDTO)
                .map(a -> modelMapper.map(a, Allergen.class));
    }

    public Optional<AllergenDTO> convertToDTO(final Optional<Allergen> allergen) {
        return Optional.ofNullable(allergen)
                .map(a -> modelMapper.map(a, AllergenDTO.class));
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

    public Optional<AllergenDTO> findByName(final String name) {
        log.info("AllergenMapper - findByName() -> Searching allergen where name is '{}' ..", name);
        return Optional.ofNullable(name)
                .map(allergenRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public Optional<AllergenDTO> save(final AllergenDTO allergenDTO) {
        log.info("AllergenMapper - save() -> Saving allergen on db..");
        return Optional.ofNullable(allergenDTO)
                .map(this::convertToEntity)
                .map(allergenRepository::save)
                .map(this::convertToDTO);
    }

    public List<AllergenDTO> findAll() {
        log.info("AllergenMapper - findAll() -> Looking for all the allergens on db..");
        return allergenRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}
