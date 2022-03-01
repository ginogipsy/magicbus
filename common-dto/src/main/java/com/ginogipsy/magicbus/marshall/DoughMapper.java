package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Dough;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.repository.DoughRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DoughMapper {

    private final ModelMapper modelMapper;
    private final DoughRepository doughRepository;

    public DoughMapper(ModelMapper modelMapper, DoughRepository doughRepository) {
        this.modelMapper = modelMapper;
        this.doughRepository = doughRepository;
    }

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

    public DoughDTO findByName(final String name) {
        log.info("Searching dough where the name is " + name + "..");
        return Optional.ofNullable(name)
                .map(doughRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public DoughDTO save(final DoughDTO doughDTO) {
        log.info("Saving allergen on db..");
        return Optional.ofNullable(doughDTO)
                .map(this::convertToEntity)
                .map(doughRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<DoughDTO> findAll() {
        return doughRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
}
