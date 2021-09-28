package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.TipologiaPagamento;
import com.ginogipsy.magicbus.dto.TipologiaPagamentoDTO;
import com.ginogipsy.magicbus.repository.TipologiaPagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TipologiaPagamentoMapper {

    private final ModelMapper modelMapper;
    private final TipologiaPagamentoRepository tipologiaPagamentoRepository;

    public TipologiaPagamentoMapper(ModelMapper modelMapper, TipologiaPagamentoRepository tipologiaPagamentoRepository) {
        this.modelMapper = modelMapper;
        this.tipologiaPagamentoRepository = tipologiaPagamentoRepository;
    }

    public TipologiaPagamento convertToEntity(TipologiaPagamentoDTO tipologiaPagamentoDTO){
        return (tipologiaPagamentoDTO != null) ? modelMapper.map(tipologiaPagamentoDTO, TipologiaPagamento.class) : null;
    }

    public TipologiaPagamentoDTO convertToDTO(TipologiaPagamento tipologiaPagamento){
        return (tipologiaPagamento != null) ? modelMapper.map(tipologiaPagamento, TipologiaPagamentoDTO.class) : null;
    }
}
