package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.PaymentType;
import com.ginogipsy.magicbus.dto.PaymentTypeDTO;
import com.ginogipsy.magicbus.repository.PaymentTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentTypeMapper {

    private final ModelMapper modelMapper;
    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeMapper(ModelMapper modelMapper, PaymentTypeRepository paymentTypeRepository) {
        this.modelMapper = modelMapper;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public PaymentType convertToEntity(PaymentTypeDTO paymentTypeDTO){
        return (paymentTypeDTO != null) ? modelMapper.map(paymentTypeDTO, PaymentType.class) : null;
    }

    public PaymentTypeDTO convertToDTO(PaymentType paymentType){
        return (paymentType != null) ? modelMapper.map(paymentType, PaymentTypeDTO.class) : null;
    }
}
