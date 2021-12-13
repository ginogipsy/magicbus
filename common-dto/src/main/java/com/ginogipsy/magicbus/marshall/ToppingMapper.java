package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.repository.TasteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToppingMapper {

    private final ModelMapper modelMapper;
    private final TasteRepository tasteRepository;

    public ToppingMapper(ModelMapper modelMapper, TasteRepository tasteRepository) {
        this.modelMapper = modelMapper;
        this.tasteRepository = tasteRepository;
    }

    public List<ToppingDTO> findByNomeContains(final String name){
        return tasteRepository.findByNameContains(name).stream().map(this::convertToDTO).toList();
    }

    public ToppingDTO findByName(final String name){
        return convertToDTO(tasteRepository.findByName(name));
    }

    public List<ToppingDTO> findByStatus(final String status) {
        return tasteRepository.findByStatus(Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }

    public List<ToppingDTO> findByBase(final String base) {
        return tasteRepository.findByBase(Base.valueOf(base)).stream().map(this::convertToDTO).toList();
    }

    public List<ToppingDTO> findByAvailabilityPeriod(final String availabilityPeriod) {
        return tasteRepository.findByAvailabilityPeriod(AvailabilityPeriod.getAvailabilityPeriod(availabilityPeriod))
                .stream().map(this::convertToDTO).toList();
    }

    public List<ToppingDTO> findByProductCategory(final String productCategory) {
        return tasteRepository.findByProductCategory(ProductCategory.getProductCategory(productCategory))
                .stream().map(this::convertToDTO).toList();
    }

    public List<ToppingDTO> findByAvailableAndStatus(final boolean available, final String status) {
        return tasteRepository.findByAvailableAndStatus(available, Status.getStatus(status))
                .stream().map(this::convertToDTO).toList();
    }

    public List<ToppingDTO> findByAvailableAndAvailabilityPeriod(final boolean available, final String availabilityPeriod) {
        return tasteRepository.findByAvailableAndAvailabilityPeriod(available, AvailabilityPeriod.getAvailabilityPeriod(availabilityPeriod))
                .stream().map(this::convertToDTO).toList();
    }

    public List<ToppingDTO> findByUserEntered(boolean userEntered){
        return tasteRepository.findByUserEntered(userEntered).stream().map(this::convertToDTO).toList();
    }

    public List<ToppingDTO> findByUserEnteredAndStatus(boolean userEntered, String status){
        return tasteRepository.findByUserEnteredAndStatus(userEntered, Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }

    public ToppingDTO convertToDTO(final Topping topping){
        return (topping != null) ? modelMapper.map(topping, ToppingDTO.class) : null;
    }

    public Topping convertToEntity(final ToppingDTO toppingDTO){
        return (toppingDTO != null) ? modelMapper.map(toppingDTO, Topping.class) : null;
    }

    public ToppingDTO save(final ToppingDTO toppingDTO){
        return convertToDTO(tasteRepository.save(convertToEntity(toppingDTO)));
    }
}
