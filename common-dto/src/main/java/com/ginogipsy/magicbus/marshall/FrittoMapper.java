package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.FrittoDTO;
import com.ginogipsy.magicbus.repository.FriedRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FrittoMapper {

    private final ModelMapper modelMapper;
    private final FriedRepository friedRepository;

    public FrittoMapper(ModelMapper modelMapper, FriedRepository friedRepository) {
        this.modelMapper = modelMapper;
        this.friedRepository = friedRepository;
    }

    public Fried convertToEntity(final FrittoDTO frittoDTO){
        return (frittoDTO != null) ? modelMapper.map(frittoDTO, Fried.class) : null;
    }

    public FrittoDTO convertToDTO(final Fried fried){
        return (fried != null) ? modelMapper.map(fried, FrittoDTO.class) : null;
    }

    public FrittoDTO findByName(final String nome){
        return convertToDTO(friedRepository.findByNome(nome));
    }

    public List<FrittoDTO> findByStatus(final String status){
        return friedRepository.findByStatus(Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }

    public FrittoDTO save(final FrittoDTO frittoDTO){
        return convertToDTO(friedRepository.save(convertToEntity(frittoDTO)));
    }


}
