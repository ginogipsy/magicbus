package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Winery;
import com.ginogipsy.magicbus.dto.WineryDTO;
import com.ginogipsy.magicbus.repository.WineryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WineryMapper {

    private final ModelMapper modelMapper;
    private final WineryRepository wineryRepository;

    public WineryMapper(ModelMapper modelMapper, WineryRepository wineryRepository) {
        this.modelMapper = modelMapper;
        this.wineryRepository = wineryRepository;
    }

    public Winery convertToEntity(WineryDTO wineryDTO){
        return (wineryDTO != null) ? modelMapper.map(wineryDTO, Winery.class) : null;
    }

    public WineryDTO convertToDTO(Winery winery){
        return (winery != null) ? modelMapper.map(winery, WineryDTO.class) : null;
    }
}
