package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.ToppingOrder;
import com.ginogipsy.magicbus.dto.ToppingOrderDTO;
import com.ginogipsy.magicbus.repository.TasteOrderRepository;
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
public class ToppingOrderMapper {

    private final ModelMapper modelMapper;
    private final TasteOrderRepository tasteOrderRepository;

    public ToppingOrder convertToEntity(final ToppingOrderDTO toppingOrderDTO){
        return Optional.ofNullable(toppingOrderDTO)
                .map(to -> modelMapper.map(to, ToppingOrder.class))
            .orElse(null);
    }

    public ToppingOrderDTO convertToDTO(final ToppingOrder toppingOrder){
        return Optional.ofNullable(toppingOrder)
                .map(to -> modelMapper.map(to, ToppingOrderDTO.class))
            .orElse(null);
    }
    public Optional<ToppingOrder> convertToEntity(final Optional<ToppingOrderDTO> toppingOrderDTO){
        return Optional.ofNullable(toppingOrderDTO)
                .map(to -> modelMapper.map(to, ToppingOrder.class));
    }

    public Optional<ToppingOrderDTO> convertToDTO(final Optional<ToppingOrder> toppingOrder){
        return Optional.ofNullable(toppingOrder)
                .map(to -> modelMapper.map(to, ToppingOrderDTO.class));
    }
}
