package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Winery;
import com.ginogipsy.magicbus.dto.WineryDTO;
import com.ginogipsy.magicbus.repository.WineryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WineryMapper {

    private final ModelMapper modelMapper;
    private final WineryRepository wineryRepository;

    public WineryMapper(ModelMapper modelMapper, WineryRepository wineryRepository) {
        this.modelMapper = modelMapper;
        this.wineryRepository = wineryRepository;
    }

    public Winery convertToEntity(final WineryDTO wineryDTO){
        return Optional.ofNullable(wineryDTO)
                .map(w -> modelMapper.map(w, Winery.class))
            .orElse(null);
    }

    public WineryDTO convertToDTO(Winery winery){
        return Optional.ofNullable(winery)
                .map(w -> modelMapper.map(w, WineryDTO.class))
            .orElse(null);
    }
}
