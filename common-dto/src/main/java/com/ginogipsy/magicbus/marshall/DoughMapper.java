package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.repository.DoughRepository;
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
public class DoughMapper {

    private final ModelMapper modelMapper;
    private final DoughRepository doughRepository;

    public Dough convertToEntity(final DoughDTO doughDTO){
        return Optional.ofNullable(doughDTO)
                .map(d -> modelMapper.map(d, Dough.class))
                .orElse(null);
    }

    public DoughDTO convertToDTO(final Dough dough) {
        return Optional.ofNullable(dough)
                .map(d -> modelMapper.map(d, DoughDTO.class))
                .orElse(null);
    }

    public Optional<Dough> convertToEntity(final Optional<DoughDTO> doughDTO) {
        return Optional.ofNullable(doughDTO)
                .map(d -> modelMapper.map(d, Dough.class));
    }

    public Optional<DoughDTO> convertToDTO(final Optional<Dough> dough) {
        return Optional.ofNullable(dough)
                .map(d -> modelMapper.map(d, DoughDTO.class));
    }

    public Optional<DoughDTO> findByName(final String name) {
        log.info("DoughMapper - findByName() -> Searching beer where name is '{}' ..", name);
        return Optional.ofNullable(name)
                .flatMap(doughRepository::findByName)
                .map(this::convertToDTO);
    }

    public Optional<DoughDTO> save(final DoughDTO doughDTO) {
        log.info("DoughMapper - save() -> Saving allergen on db..");
        return Optional.ofNullable(doughDTO)
                .map(this::convertToEntity)
                .map(doughRepository::save)
                .map(this::convertToDTO);
    }

    public List<DoughDTO> findAll() {
        return doughRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}
