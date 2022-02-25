package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.PaymentType;
import com.ginogipsy.magicbus.dto.PaymentTypeDTO;
import com.ginogipsy.magicbus.repository.PaymentTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentTypeMapper {

    private final ModelMapper modelMapper;
    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeMapper(ModelMapper modelMapper, PaymentTypeRepository paymentTypeRepository) {
        this.modelMapper = modelMapper;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public PaymentType convertToEntity(final PaymentTypeDTO paymentTypeDTO){
        return Optional.ofNullable(paymentTypeDTO)
                .map(pt -> modelMapper.map(pt, PaymentType.class))
            .orElse(null);
    }

    public PaymentTypeDTO convertToDTO(final PaymentType paymentType){
        return Optional.ofNullable(paymentType)
                .map(pt -> modelMapper.map(pt, PaymentTypeDTO.class))
            .orElse(null);
    }
}
