package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.customexception.notfound.BibitaNotFoundException;
import com.ginogipsy.magicbus.domain.Drink;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.domain.enums.DrinkType;
import com.ginogipsy.magicbus.dto.BibitaDTO;
import com.ginogipsy.magicbus.repository.DrinkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BibitaMapper {

    private final ModelMapper modelMapper;
    private final DrinkRepository drinkRepository;

    public BibitaMapper(ModelMapper modelMapper, DrinkRepository drinkRepository) {
        this.modelMapper = modelMapper;
        this.drinkRepository = drinkRepository;
    }

    public Drink convertToEntity(BibitaDTO bibitaDTO){
        return (bibitaDTO != null) ? modelMapper.map(bibitaDTO, Drink.class) : null;
    }

    public BibitaDTO convertToDTO(Drink drink){
        return (drink != null) ? modelMapper.map(drink, BibitaDTO.class) : null;
    }

    public BibitaDTO findByNome(String nome){
        return Optional.ofNullable(drinkRepository.findByNome(nome))
                .map(this::convertToDTO)
                .orElseThrow(() -> new BibitaNotFoundException("Bibita "+nome+" non trovata!"));
    }

    List<BibitaDTO> findByTipologiaBibite(String tipologiaBibite){
        return drinkRepository.findByTipologia(DrinkType.valueOf(tipologiaBibite))
                .stream().map(this::convertToDTO).toList();

    }

    List<BibitaDTO> findByStatus(String status){
        return Optional.of(drinkRepository.findByStatus(Status.valueOf(status)))
                .map(list -> list.stream().map(this::convertToDTO).toList())
                .orElseThrow(() -> new BibitaNotFoundException("Bibite per "+status+" non trovato!"));
    }

    List<BibitaDTO> findByStatusAndTipologiaBibite(String status, String tipologiaBibite){

        return Optional.of(drinkRepository.findByStatusAndTipologia(Status.valueOf(status), DrinkType.valueOf(tipologiaBibite)))
                .map(list -> list.stream().map(this::convertToDTO).toList())
                .orElseThrow(() -> new BibitaNotFoundException("Bibite per status "+status+" e tipologia "+tipologiaBibite+" non trovate!"));
    }

}
