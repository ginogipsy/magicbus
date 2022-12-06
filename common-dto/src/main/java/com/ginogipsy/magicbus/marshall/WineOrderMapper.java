package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.WineOrder;
import com.ginogipsy.magicbus.dto.WineOrderDTO;
import com.ginogipsy.magicbus.repository.WineOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class WineOrderMapper {

    private final ModelMapper modelMapper;
    private final WineOrderRepository wineOrderRepository;

    public Optional<WineOrder> convertToEntity(final Optional<WineOrderDTO> wineOrderDTO){
        return Optional.ofNullable(wineOrderDTO)
                .map(wo -> modelMapper.map(wo, WineOrder.class));
    }

    public Optional<WineOrderDTO> convertToDTO(final Optional<WineOrder> wineOrder){
        return Optional.ofNullable(wineOrder)
                .map(wo -> modelMapper.map(wo, WineOrderDTO.class));
    }
}
