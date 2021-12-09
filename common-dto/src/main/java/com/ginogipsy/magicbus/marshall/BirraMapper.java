package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.customexception.notfound.BirraNotFoundException;
import com.ginogipsy.magicbus.domain.Beer;

import com.ginogipsy.magicbus.domain.enums.BeerType;
import com.ginogipsy.magicbus.dto.BirraDTO;
import com.ginogipsy.magicbus.repository.BirraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BirraMapper {

    private final ModelMapper modelMapper;
    private final BirraRepository birraRepository;

    public BirraMapper(ModelMapper modelMapper, BirraRepository birraRepository) {
        this.modelMapper = modelMapper;
        this.birraRepository = birraRepository;
    }

    public Beer convertToEntity(BirraDTO birraDTO){
        return (birraDTO != null) ? modelMapper.map(birraDTO, Beer.class) : null;
    }

    public BirraDTO convertToDTO(Beer beer){
        return (beer != null) ? modelMapper.map(beer, BirraDTO.class) : null;
    }

    public BirraDTO findByNome(String nome){
        return Optional.ofNullable(birraRepository.findByNome(nome)).map(this::convertToDTO).orElseThrow(() -> new BirraNotFoundException("Birra "+nome+" non disponibile!"));
    }

    public List<BirraDTO> findByNomeBirrificio(String nomeBirrificio){
        return Optional.of(birraRepository.findByBirrificio_Nome(nomeBirrificio).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre del birrificio "+nomeBirrificio+" non disponibili!"));
    }

    public List<BirraDTO> findByTipologiaBirra(String tipologiaBirra){
        return Optional.of(birraRepository.findByTipologiaBirra(BeerType.valueOf(tipologiaBirra)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre della tipologia "+tipologiaBirra+" non disponibili!"));
    }

    public List<BirraDTO> findByDisponibile(boolean disponibile){
        return Optional.of(birraRepository.findByDisponibile(disponibile).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre  non disponibili!"));
    }

    public List<BirraDTO> findByDisponibileAndAndBirrificio_Nome(boolean disponibile, String nomeBirrificio){
        return Optional.of(birraRepository.findByDisponibileAndAndBirrificio_Nome(disponibile, nomeBirrificio).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre del birrificio disponibili "+nomeBirrificio+" non trovate!"));
    }

    public List<BirraDTO> findByDisponibileAndTipologiaBirra(boolean disponibile, String tipologiaBirra){
        return Optional.of(birraRepository.findByDisponibileAndTipologiaBirra(disponibile, BeerType.valueOf(tipologiaBirra)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre disponibili del tipo "+tipologiaBirra+" non trovate!"));
    }


}
