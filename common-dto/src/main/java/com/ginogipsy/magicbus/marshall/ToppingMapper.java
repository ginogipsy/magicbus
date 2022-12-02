package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.repository.TasteRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ToppingMapper {

    private final ModelMapper modelMapper;
    private final TasteRepository tasteRepository;

    public ToppingMapper(ModelMapper modelMapper, TasteRepository tasteRepository) {
        this.modelMapper = modelMapper;
        this.tasteRepository = tasteRepository;
    }

    public List<ToppingDTO> findByNomeContains(final String name){
        log.info("Searching list of topping that contains *"+name+"* in their names");
        return Optional.ofNullable(name)
                .map(n -> tasteRepository.findByNameContains(n)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public ToppingDTO findByName(final String name){
        log.info("Search topping where name is "+name+"..");
        return Optional.ofNullable(name)
                .map(tasteRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<ToppingDTO> findByStatus(final String status) {
        log.info("Searching list of topping where status is "+status+"..");
        return Optional.ofNullable(status)
                .map(s -> tasteRepository.findByStatus(StatusEnum.getStatus(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByBase(final String base) {
        log.info("Searching list of topping where base is "+base+"..");
        return Optional.ofNullable(base)
                .map(b -> tasteRepository.findByBase(BaseEnum.valueOf(b))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByAvailabilityPeriod(final String availabilityPeriod) {
        log.info("Searching list of topping where availability period is "+ availabilityPeriod+"..");
        return Optional.ofNullable(availabilityPeriod)
                .map(ap -> tasteRepository.findByAvailabilityPeriod(AvailabilityPeriodEnum.getAvailabilityPeriod(ap))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByProductCategory(final String productCategory) {
        log.info("Searching list of topping where product category is "+ productCategory+"..");
        return Optional.ofNullable(productCategory)
                .map(pc -> tasteRepository.findByProductCategory(ProductCategory.getProductCategory(pc))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByAvailableAndStatus(final boolean available, final String status) {
        log.info("Searching list of topping where availability is "+available +" and status is " +status+"..");
        return Optional.ofNullable(status)
                .map(s -> tasteRepository.findByAvailableAndStatus(available, StatusEnum.getStatus(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByAvailableAndAvailabilityPeriod(final boolean available, final String availabilityPeriod) {
        log.info("Searching list of topping where availability is "+available +" and availability period is "+availabilityPeriod+"..");
        return Optional.ofNullable(availabilityPeriod)
                .map(ap -> tasteRepository.findByAvailableAndAvailabilityPeriod(available, AvailabilityPeriodEnum.getAvailabilityPeriod(ap))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByUserEntered(final boolean userEntered){
        log.info("Searching list of topping where user entered is "+userEntered +"..");
        return tasteRepository.findByUserEntered(userEntered)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<ToppingDTO> findByUserEnteredAndStatus(boolean userEntered, String status){
        log.info("Searching list of topping where user entered is "+userEntered +" and status is "+status+"..");
        return Optional.ofNullable(status)
                .map(s -> tasteRepository.findByUserEnteredAndStatus(userEntered, StatusEnum.getStatus(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public ToppingDTO save(final ToppingDTO toppingDTO){
        log.info("Saving topping on db..");
        return Optional.ofNullable(toppingDTO)
                .map(this::convertToEntity)
                .map(tasteRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public ToppingDTO convertToDTO(final Topping topping){
        return Optional.ofNullable(topping)
                .map(t -> modelMapper.map(t, ToppingDTO.class))
                .orElse(null);
    }

    public Topping convertToEntity(final ToppingDTO toppingDTO){
        return Optional.ofNullable(toppingDTO)
                .map(t -> modelMapper.map(t, Topping.class))
                .orElse(null);
    }
}
