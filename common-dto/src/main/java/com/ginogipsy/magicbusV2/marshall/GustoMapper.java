package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Gusto;
import com.ginogipsy.magicbusV2.dto.GustoDTO;
import com.ginogipsy.magicbusV2.repository.GustoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GustoMapper {

    private final ModelMapper modelMapper;
    private final GustoRepository gustoRepository;

    public GustoMapper(ModelMapper modelMapper, GustoRepository gustoRepository) {
        this.modelMapper = modelMapper;
        this.gustoRepository = gustoRepository;
    }

    public GustoDTO findByNome(String nome){
        return convertToDTO(gustoRepository.findByNome(nome));
    }

    public GustoDTO convertToDTO(Gusto gusto){
        return (gusto != null) ? modelMapper.map(gusto, GustoDTO.class) : null;
    }
}
