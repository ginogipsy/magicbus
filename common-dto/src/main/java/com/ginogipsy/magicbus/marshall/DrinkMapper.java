package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.customexception.notfound.BibitaNotFoundException;
import com.ginogipsy.magicbus.domain.Drink;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.DrinkType;
import com.ginogipsy.magicbus.dto.DrinkDTO;
import com.ginogipsy.magicbus.repository.DrinkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DrinkMapper {

    private final ModelMapper modelMapper;
    private final DrinkRepository drinkRepository;

    public DrinkMapper(ModelMapper modelMapper, DrinkRepository drinkRepository) {
        this.modelMapper = modelMapper;
        this.drinkRepository = drinkRepository;
    }

    public Drink convertToEntity(DrinkDTO drinkDTO){
        return (drinkDTO != null) ? modelMapper.map(drinkDTO, Drink.class) : null;
    }

    public DrinkDTO convertToDTO(Drink drink){
        return (drink != null) ? modelMapper.map(drink, DrinkDTO.class) : null;
    }

    public DrinkDTO findByName(String name){
        return Optional.ofNullable(drinkRepository.findByName(name))
                .map(this::convertToDTO)
                .orElseThrow(() -> new BibitaNotFoundException("Bibita "+name+" non trovata!"));
    }

    List<DrinkDTO> findByDrinkType(String drinkType){
        return drinkRepository.findByDrinkType(DrinkType.valueOf(drinkType))
                .stream().map(this::convertToDTO).toList();

    }

    List<DrinkDTO> findByStatus(String status){
        return Optional.of(drinkRepository.findByStatus(Status.valueOf(status)))
                .map(list -> list.stream().map(this::convertToDTO).toList())
                .orElseThrow(() -> new BibitaNotFoundException("Bibite per "+status+" non trovato!"));
    }

    List<DrinkDTO> findByStatusAndType(String status, String drinkType){

        return Optional.of(drinkRepository.findByStatusAndDrinkType(Status.valueOf(status), DrinkType.valueOf(drinkType)))
                .map(list -> list.stream().map(this::convertToDTO).toList())
                .orElseThrow(() -> new BibitaNotFoundException("Bibite per status "+status+" e tipologia "+drinkType+" non trovate!"));
    }

}
