package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.repository.FriedRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class FriedMapper {

    private final ModelMapper modelMapper;
    private final FriedRepository friedRepository;

    public FriedMapper(ModelMapper modelMapper, FriedRepository friedRepository) {
        this.modelMapper = modelMapper;
        this.friedRepository = friedRepository;
    }

    public Fried convertToEntity(final FriedDTO friedDTO) {
        return Optional.ofNullable(friedDTO)
                .map(f -> modelMapper.map(f, Fried.class))
                .orElse(null);
    }

    public FriedDTO convertToDTO(final Fried fried) {
        return Optional.ofNullable(fried)
                .map(f -> modelMapper.map(f, FriedDTO.class))
                .orElse(null);
    }

    public FriedDTO findByName(final String name) {
        log.info("Searching fried where name is " + name+ "..");
        return Optional.ofNullable(name)
                .map(friedRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<FriedDTO> findByStatus(final String status) {
        log.info("Searching fried where status is " + status+ "..");
        return Optional.ofNullable(status)
                .map(s -> friedRepository.findByStatus(Status.getStatus(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public FriedDTO save(final FriedDTO friedDTO) {
        log.info("Saving fried ..");
        return Optional.ofNullable(friedDTO)
                .map(f -> friedRepository.save(convertToEntity(f)))
                .map(this::convertToDTO)
                .orElse(null);
    }


}
