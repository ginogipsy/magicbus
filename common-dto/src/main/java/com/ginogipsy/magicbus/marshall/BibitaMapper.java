package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Bibita;
import com.ginogipsy.magicbus.dto.BibitaDTO;
import com.ginogipsy.magicbus.repository.BibitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BibitaMapper {

    private final ModelMapper modelMapper;
    private final BibitaRepository bibitaRepository;

    public BibitaMapper(ModelMapper modelMapper, BibitaRepository bibitaRepository) {
        this.modelMapper = modelMapper;
        this.bibitaRepository = bibitaRepository;
    }

    public Bibita convertToEntity(BibitaDTO bibitaDTO){
        return (bibitaDTO != null) ? modelMapper.map(bibitaDTO, Bibita.class) : null;
    }

    public BibitaDTO convertToDTO(Bibita bibita){
        return (bibita != null) ? modelMapper.map(bibita, BibitaDTO.class) : null;
    }
}
