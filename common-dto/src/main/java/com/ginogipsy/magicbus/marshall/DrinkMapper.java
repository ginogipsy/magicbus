package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Drink;
import com.ginogipsy.magicbus.domain.enums.DrinkType;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.DrinkDTO;
import com.ginogipsy.magicbus.repository.DrinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class DrinkMapper {

    private final ModelMapper modelMapper;
    private final DrinkRepository drinkRepository;

    public DrinkMapper(ModelMapper modelMapper, DrinkRepository drinkRepository) {
        this.modelMapper = modelMapper;
        this.drinkRepository = drinkRepository;
    }

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

    public DrinkDTO findByName(final String name){
        log.info("Searching drink where name is " + name+ "..");
        return Optional.ofNullable(name)
                .map(drinkRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<DrinkDTO> findByDrinkType(final String drinkType){
        log.info("Searching drinks where drink type is " + drinkType+ "..");
        return Optional.ofNullable(drinkType)
                .map(dt -> drinkRepository.findByDrinkType(DrinkType.valueOf(dt))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<DrinkDTO> findByStatus(final String status){
        log.info("Searching drinks where status is " + status+ "..");
        return Optional.ofNullable(status)
                .map(s -> drinkRepository.findByStatus(Status.valueOf(s))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    List<DrinkDTO> findByStatusAndType(final String status, final String drinkType){
        log.info("Searching drinks where status is " + status+ "and drink type is "+drinkType+"..");
        return Optional.ofNullable(status)
                .filter(s -> Objects.nonNull(drinkType))
                .map(s -> drinkRepository.findByStatusAndDrinkType(Status.valueOf(s), DrinkType.valueOf(drinkType))
                                .stream()
                                .map(this::convertToDTO)
                                .toList())
                .orElse(new ArrayList<>());
    }

}
