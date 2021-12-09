package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Winery;
import com.ginogipsy.magicbus.dto.CantinaDTO;
import com.ginogipsy.magicbus.repository.WineryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CantinaMapper {

    private final ModelMapper modelMapper;
    private final WineryRepository wineryRepository;

    public CantinaMapper(ModelMapper modelMapper, WineryRepository wineryRepository) {
        this.modelMapper = modelMapper;
        this.wineryRepository = wineryRepository;
    }

    public Winery convertToEntity(CantinaDTO cantinaDTO){
        return (cantinaDTO != null) ? modelMapper.map(cantinaDTO, Winery.class) : null;
    }

    public CantinaDTO convertToDTO(Winery winery){
        return (winery != null) ? modelMapper.map(winery, CantinaDTO.class) : null;
    }
}
