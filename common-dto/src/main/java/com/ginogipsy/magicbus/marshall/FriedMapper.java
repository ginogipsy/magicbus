package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.enums.StatusEnum;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.repository.FriedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class FriedMapper {

    private final ModelMapper modelMapper;
    private final FriedRepository friedRepository;

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
public Optional<Fried> convertToEntity(final Optional<FriedDTO> friedDTO) {
        return Optional.ofNullable(friedDTO)
                .map(f -> modelMapper.map(f, Fried.class));
    }

    public Optional<FriedDTO> convertToDTO(final Optional<Fried> fried) {
        return Optional.ofNullable(fried)
                .map(f -> modelMapper.map(f, FriedDTO.class));
    }

    public Optional<FriedDTO> findByName(final String name) {
        log.info("FriedMapper - findByName() -> Searching fried where name is '{}' ..", name);
        return Optional.ofNullable(name)
                .flatMap(friedRepository::findByName)
                .map(this::convertToDTO);
    }

    public List<FriedDTO> findByStatus(final String status) {
        log.info("FriedMapper - findByName() -> Searching fried where status is {} .. ", status);
        return Optional.ofNullable(status)
                .map(s -> friedRepository.findByStatus(StatusEnum.getStatus(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public FriedDTO save(final FriedDTO friedDTO) {
        log.info("FriedMapper - findByName() -> fried ..");
        return Optional.ofNullable(friedDTO)
                .map(f -> friedRepository.save(convertToEntity(f)))
                .map(this::convertToDTO)
                .orElse(null);
    }


}
