package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.customexception.notfound.FrittoNotFoundException;
import com.ginogipsy.magicbus.domain.Fritto;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.FrittoDTO;
import com.ginogipsy.magicbus.repository.FrittoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FrittoMapper {

    private final ModelMapper modelMapper;
    private final FrittoRepository frittoRepository;

    public FrittoMapper(ModelMapper modelMapper, FrittoRepository frittoRepository) {
        this.modelMapper = modelMapper;
        this.frittoRepository = frittoRepository;
    }

    public Fritto convertToEntity(final FrittoDTO frittoDTO){
        return (frittoDTO != null) ? modelMapper.map(frittoDTO, Fritto.class) : null;
    }

    public FrittoDTO convertToDTO(final Fritto fritto){
        return (fritto != null) ? modelMapper.map(fritto, FrittoDTO.class) : null;
    }

    public FrittoDTO findByName(final String nome){
        return Optional.ofNullable(frittoRepository.findByNome(nome)).map(this::convertToDTO).orElseThrow(() -> new FrittoNotFoundException("Fritto non trovato"));
    }

    public List<FrittoDTO> findByStatus(final String status){
        return Optional.of(frittoRepository.findByStatus(Status.valueOf(status)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new FrittoNotFoundException("Fritto non trovato"));
    }

}
