package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import com.ginogipsy.magicbus.dto.TasteDTO;
import com.ginogipsy.magicbus.repository.TasteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TasteMapper {

    private final ModelMapper modelMapper;
    private final TasteRepository tasteRepository;

    public TasteMapper(ModelMapper modelMapper, TasteRepository tasteRepository) {
        this.modelMapper = modelMapper;
        this.tasteRepository = tasteRepository;
    }

    public List<TasteDTO> findByNomeContains(final String name){
        return tasteRepository.findByNameContains(name).stream().map(this::convertToDTO).toList();
    }

    public TasteDTO findByName(final String name){
        return convertToDTO(tasteRepository.findByName(name));
    }

    public List<TasteDTO> findByStatus(final String status) {
        return tasteRepository.findByStatus(Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }

    public List<TasteDTO> findByBase(final String base) {
        return tasteRepository.findByBase(Base.valueOf(base)).stream().map(this::convertToDTO).toList();
    }

    public List<TasteDTO> findByAvailabilityPeriod(final String availabilityPeriod) {
        return tasteRepository.findByAvailabilityPeriod(AvailabilityPeriod.getAvailabilityPeriod(availabilityPeriod))
                .stream().map(this::convertToDTO).toList();
    }

    public List<TasteDTO> findByProductCategory(final String productCategory) {
        return tasteRepository.findByProductCategory(ProductCategory.valueOf(productCategory))
                .stream().map(this::convertToDTO).toList();
    }

    public List<TasteDTO> findByAvailableAndStatus(final boolean available, final String status) {
        return tasteRepository.findByAvailableAndStatus(available, Status.getStatus(status))
                .stream().map(this::convertToDTO).toList();
    }

    public List<TasteDTO> findByAvailableAndAvailabilityPeriod(final boolean available, final String availabilityPeriod) {
        return tasteRepository.findByAvailableAndAvailabilityPeriod(available, AvailabilityPeriod.valueOf(availabilityPeriod))
                .stream().map(this::convertToDTO).toList();
    }

    public List<TasteDTO> findByUserEntered(boolean userEntered){
        return tasteRepository.findByUserEntered(userEntered).stream().map(this::convertToDTO).toList();
    }

    public List<TasteDTO> findByUserEnteredAndStatus(boolean userEntered, String status){
        return tasteRepository.findByUserEnteredAndStatus(userEntered, Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }

    public TasteDTO convertToDTO(final Taste taste){
        return (taste != null) ? modelMapper.map(taste, TasteDTO.class) : null;
    }

    public Taste convertToEntity(final TasteDTO tasteDTO){
        return (tasteDTO != null) ? modelMapper.map(tasteDTO, Taste.class) : null;
    }

    public TasteDTO save(final TasteDTO tasteDTO){
        return convertToDTO(tasteRepository.save(convertToEntity(tasteDTO)));
    }
}
