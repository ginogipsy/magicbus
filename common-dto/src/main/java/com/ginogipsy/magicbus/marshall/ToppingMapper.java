package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.repository.TasteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ToppingMapper {

    private final ModelMapper modelMapper;
    private final TasteRepository tasteRepository;

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

    public Optional<ToppingDTO> convertToDTO(final Optional<Topping> topping){
        return Optional.ofNullable(topping)
                .map(t -> modelMapper.map(t, ToppingDTO.class));
    }

    public Optional<Topping> convertToEntity(final Optional<ToppingDTO> toppingDTO){
        return Optional.ofNullable(toppingDTO)
                .map(t -> modelMapper.map(t, Topping.class));
    }

    public List<ToppingDTO> findByNomeContains(final String name){
        log.info("ToppingMapper - findByNameContains() -> Searching list of toppings that their name contains {}..", name);
        return Optional.ofNullable(name)
                .map(n -> tasteRepository.findByNameContains(n)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public Optional<ToppingDTO> findByName(final String name){
        log.info("ToppingMapper - findByName() -> Searching topping where name is '{}' ..", name);
        return Optional.ofNullable(name)
                .flatMap(tasteRepository::findByName)
                .map(this::convertToDTO);
    }

    public List<ToppingDTO> findByStatus(final String status) {
        log.info("ToppingMapper - findByStatus() -> Searching list of topping where status is {}..", status);
        return Optional.ofNullable(status)
                .map(s -> tasteRepository.findByStatusEnum(StatusEnum.getStatus(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByBase(final String base) {
        log.info("ToppingMapper - findByBase() -> Searching list of topping where base is {}..", base);
        return Optional.ofNullable(base)
                .map(b -> tasteRepository.findByBaseEnum(BaseEnum.valueOf(b))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByAvailabilityPeriod(final String availabilityPeriod) {
        log.info("ToppingMapper - findByAvailabilityPeriod() -> Searching list of topping where availability period is {}..", availabilityPeriod);
        return Optional.ofNullable(availabilityPeriod)
                .map(ap -> tasteRepository.findByAvailabilityPeriodEnum(AvailabilityPeriodEnum.getAvailabilityPeriod(ap))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByProductCategory(final String productCategory) {
        log.info("ToppingMapper - findByProductCategory() -> Searching list of topping where product category is {}..", productCategory);
        return Optional.ofNullable(productCategory)
                .map(pc -> tasteRepository.findByProductCategoryEnum(ProductCategoryEnum.getProductCategory(pc))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByAvailableAndStatus(final boolean available, final String status) {
        log.info("ToppingMapper - findByAvailableAndStatus() -> Searching list of topping where availability is {} and status is {}..", available, status);
        return Optional.ofNullable(status)
                .map(s -> tasteRepository.findByAvailableAndStatusEnum(available, StatusEnum.getStatus(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByAvailableAndAvailabilityPeriod(final boolean available, final String availabilityPeriod) {
        log.info("ToppingMapper - findByAvailableAndAvailabilityPeriod() -> Searching list of topping where availability is {} and availability period is {}..", available, availabilityPeriod);
        return Optional.ofNullable(availabilityPeriod)
                .map(ap -> tasteRepository.findByAvailableAndAvailabilityPeriodEnum(available, AvailabilityPeriodEnum.getAvailabilityPeriod(ap))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<ToppingDTO> findByUserEntered(final boolean userEntered){
        log.info("ToppingMapper - findByUserEntered() -> Searching list of topping where user entered is {}..", userEntered);
        return tasteRepository.findByUserEntered(userEntered)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<ToppingDTO> findByUserEnteredAndStatus(final boolean userEntered, final String status){
        log.info("ToppingMapper - findByUserEnteredAndStatus() -> Searching list of topping where user entered is {} and status is {}..", userEntered, status);
        return Optional.ofNullable(status)
                .map(s -> tasteRepository.findByUserEnteredAndStatusEnum(userEntered, StatusEnum.getStatus(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public Optional<ToppingDTO> save(final ToppingDTO toppingDTO){
        log.info("ToppingMapper - save() -> Saving topping on db..");
        return Optional.ofNullable(toppingDTO)
                .map(this::convertToEntity)
                .map(tasteRepository::save)
                .map(this::convertToDTO);
    }
}
