package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.PaymentType;
import com.ginogipsy.magicbus.dto.PaymentTypeDTO;
import com.ginogipsy.magicbus.repository.PaymentTypeRepository;
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
public class PaymentTypeMapper {

    private final ModelMapper modelMapper;
    private final PaymentTypeRepository paymentTypeRepository;

    public Optional<PaymentType> convertToEntity(final Optional<PaymentTypeDTO> paymentTypeDTO){
        return Optional.ofNullable(paymentTypeDTO)
                .map(pt -> modelMapper.map(pt, PaymentType.class));
    }

    public Optional<PaymentTypeDTO> convertToDTO(final Optional<PaymentType> paymentType){
        return Optional.ofNullable(paymentType)
                .map(pt -> modelMapper.map(pt, PaymentTypeDTO.class));
    }
}
