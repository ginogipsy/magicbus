package com.ginogipsy.magicbusV2.marshall;


import com.ginogipsy.magicbusV2.domain.Fornitore;
import com.ginogipsy.magicbusV2.dto.FornitoreDTO;
import com.ginogipsy.magicbusV2.repository.FornitoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FornitoreMapper {

    private final ModelMapper modelMapper;
    private final FornitoreRepository fornitoreRepository;


    public FornitoreMapper(ModelMapper modelMapper, FornitoreRepository fornitoreRepository) {
        this.modelMapper = modelMapper;
        this.fornitoreRepository = fornitoreRepository;
    }

    public Fornitore convertToEntity(FornitoreDTO fornitoreDTO){
        return (fornitoreDTO != null) ? modelMapper.map(fornitoreDTO, Fornitore.class) : null;
    }

    public FornitoreDTO convertToDTO(Fornitore fornitore){
        return (fornitore != null) ? modelMapper.map(fornitore, FornitoreDTO.class) : null;
    }
}
