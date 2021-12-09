package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Fried;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.repository.FriedRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FriedMapper {

    private final ModelMapper modelMapper;
    private final FriedRepository friedRepository;

    public FriedMapper(ModelMapper modelMapper, FriedRepository friedRepository) {
        this.modelMapper = modelMapper;
        this.friedRepository = friedRepository;
    }

    public Fried convertToEntity(final FriedDTO friedDTO){
        return (friedDTO != null) ? modelMapper.map(friedDTO, Fried.class) : null;
    }

    public FriedDTO convertToDTO(final Fried fried){
        return (fried != null) ? modelMapper.map(fried, FriedDTO.class) : null;
    }

    public FriedDTO findByName(final String name){
        return convertToDTO(friedRepository.findByName(name));
    }

    public List<FriedDTO> findByStatus(final String status){
        return friedRepository.findByStatus(Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }

    public FriedDTO save(final FriedDTO friedDTO){
        return convertToDTO(friedRepository.save(convertToEntity(friedDTO)));
    }


}
