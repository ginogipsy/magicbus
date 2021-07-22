package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.Bibita;
import com.ginogipsy.magicbusV2.domain.TipologiaPagamento;
import com.ginogipsy.magicbusV2.dto.BibitaDTO;
import com.ginogipsy.magicbusV2.dto.TipologiaPagamentoDTO;
import com.ginogipsy.magicbusV2.repository.TipologiaPagamentoRepository;
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
