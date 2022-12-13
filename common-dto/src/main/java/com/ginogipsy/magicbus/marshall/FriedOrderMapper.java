package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.FriedOrder;
import com.ginogipsy.magicbus.dto.FriedOrderDTO;
import com.ginogipsy.magicbus.repository.FriedOrderRepository;
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
public class FriedOrderMapper {

    private final ModelMapper modelMapper;
    private final FriedOrderRepository friedOrderRepository;

    public FriedOrder convertToEntity(final FriedOrderDTO friedOrderDTO){
        return Optional.ofNullable(friedOrderDTO)
                .map(fo -> modelMapper.map(fo, FriedOrder.class))
            .orElse(null);
    }

    public FriedOrderDTO convertToDTO(final FriedOrder friedOrder){
        return Optional.ofNullable(friedOrder)
                .map(fo -> modelMapper.map(fo, FriedOrderDTO.class))
            .orElse(null);
    }
    public Optional<FriedOrder> convertToEntity(final Optional<FriedOrderDTO> friedOrderDTO){
        return Optional.ofNullable(friedOrderDTO)
                .map(fo -> modelMapper.map(fo, FriedOrder.class));
    }

    public Optional<FriedOrderDTO> convertToDTO(final Optional<FriedOrder> friedOrder){
        return Optional.ofNullable(friedOrder)
                .map(fo -> modelMapper.map(fo, FriedOrderDTO.class));
    }
}
