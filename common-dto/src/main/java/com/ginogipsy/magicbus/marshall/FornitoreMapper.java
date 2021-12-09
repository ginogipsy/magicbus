package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Supplier;
import com.ginogipsy.magicbus.dto.FornitoreDTO;
import com.ginogipsy.magicbus.repository.FornitoreRepository;
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

    public Supplier convertToEntity(FornitoreDTO fornitoreDTO){
        return (fornitoreDTO != null) ? modelMapper.map(fornitoreDTO, Supplier.class) : null;
    }

    public FornitoreDTO convertToDTO(Supplier supplier){
        return (supplier != null) ? modelMapper.map(supplier, FornitoreDTO.class) : null;
    }
}
