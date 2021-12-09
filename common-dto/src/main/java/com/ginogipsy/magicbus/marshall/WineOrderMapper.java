package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.WineOrder;
import com.ginogipsy.magicbus.dto.WineOrderDTO;
import com.ginogipsy.magicbus.repository.WineOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WineOrderMapper {

    private final ModelMapper modelMapper;
    private final WineOrderRepository wineOrderRepository;

    public WineOrderMapper(ModelMapper modelMapper, WineOrderRepository wineOrderRepository) {
        this.modelMapper = modelMapper;
        this.wineOrderRepository = wineOrderRepository;
    }

    public WineOrder convertToEntity(WineOrderDTO wineOrderDTO){
        return (wineOrderDTO != null) ? modelMapper.map(wineOrderDTO, WineOrder.class) : null;
    }

    public WineOrderDTO convertToDTO(WineOrder wineOrder){
        return (wineOrder != null) ? modelMapper.map(wineOrder, WineOrderDTO.class) : null;
    }
}
