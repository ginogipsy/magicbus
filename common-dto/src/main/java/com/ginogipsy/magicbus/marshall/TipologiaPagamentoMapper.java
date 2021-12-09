package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.PaymentType;
import com.ginogipsy.magicbus.dto.TipologiaPagamentoDTO;
import com.ginogipsy.magicbus.repository.PaymentTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TipologiaPagamentoMapper {

    private final ModelMapper modelMapper;
    private final PaymentTypeRepository paymentTypeRepository;

    public TipologiaPagamentoMapper(ModelMapper modelMapper, PaymentTypeRepository paymentTypeRepository) {
        this.modelMapper = modelMapper;
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public PaymentType convertToEntity(TipologiaPagamentoDTO tipologiaPagamentoDTO){
        return (tipologiaPagamentoDTO != null) ? modelMapper.map(tipologiaPagamentoDTO, PaymentType.class) : null;
    }

    public TipologiaPagamentoDTO convertToDTO(PaymentType paymentType){
        return (paymentType != null) ? modelMapper.map(paymentType, TipologiaPagamentoDTO.class) : null;
    }
}
