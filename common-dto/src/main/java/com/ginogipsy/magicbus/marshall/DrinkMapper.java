package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Drink;
import com.ginogipsy.magicbus.domain.enums.DrinkTypeEnum;
import com.ginogipsy.magicbus.domain.enums.StatusEnum;
import com.ginogipsy.magicbus.dto.DrinkDTO;
import com.ginogipsy.magicbus.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class DrinkMapper {

    private final ModelMapper modelMapper;
    private final DrinkRepository drinkRepository;

    public Drink convertToEntity(final DrinkDTO drinkDTO){
        return Optional.ofNullable(drinkDTO)
                .map(d -> modelMapper.map(d, Drink.class))
            .orElse(null);
    }

    public DrinkDTO convertToDTO(final Drink drink){
        return Optional.ofNullable(drink)
                .map(d -> modelMapper.map(d, DrinkDTO.class))
            .orElse(null);
    }
 public Optional<Drink> convertToEntity(final Optional<DrinkDTO> drinkDTO){
        return Optional.ofNullable(drinkDTO)
                .map(d -> modelMapper.map(d, Drink.class));
    }

    public Optional<DrinkDTO> convertToDTO(final Optional<Drink> drink){
        return Optional.ofNullable(drink)
                .map(d -> modelMapper.map(d, DrinkDTO.class));
    }

    public Optional<DrinkDTO> findByName(final String name){
        log.info("DrinkMapper - findByName() -> Searching beers where breweryName is '{}' ..", name);
        return Optional.ofNullable(name)
                .flatMap(drinkRepository::findByName)
                .map(this::convertToDTO);
    }

    public List<DrinkDTO> findByDrinkType(final String drinkType){
        log.info("DrinkMapper - findByDrinkType() -> Searching drinks where drink type is " + drinkType+ "..");
        return Optional.ofNullable(drinkType)
                .map(dt -> drinkRepository.findByDrinkTypeEnum(DrinkTypeEnum.valueOf(dt))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<DrinkDTO> findByStatus(final String status){
        log.info("DrinkMapper - findByStatus() -> Searching drinks where status is " + status+ "..");
        return Optional.ofNullable(status)
                .map(s -> drinkRepository.findByStatusEnum(StatusEnum.valueOf(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    List<DrinkDTO> findByStatusAndType(final String status, final String drinkTypeEnum){
        log.info("DrinkMapper - findByStatusAndType() -> Searching drinks where status is " + status+ "and drink type is "+drinkTypeEnum+"..");
        return Optional.ofNullable(status)
                .filter(s -> Objects.nonNull(drinkTypeEnum))
                .map(s -> drinkRepository.findByStatusEnumAndDrinkTypeEnum(StatusEnum.valueOf(s), DrinkTypeEnum.valueOf(drinkTypeEnum))
                                .stream()
                                .map(this::convertToDTO)
                                .toList())
                .orElse(new ArrayList<>());
    }

}
