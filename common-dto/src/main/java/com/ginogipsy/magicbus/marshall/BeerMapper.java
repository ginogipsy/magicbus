package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.customexception.notfound.BirraNotFoundException;
import com.ginogipsy.magicbus.domain.Beer;

import com.ginogipsy.magicbus.domain.enums.BeerType;
import com.ginogipsy.magicbus.dto.BeerDTO;
import com.ginogipsy.magicbus.repository.BeerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BeerMapper {

    private final ModelMapper modelMapper;
    private final BeerRepository beerRepository;

    public BeerMapper(ModelMapper modelMapper, BeerRepository beerRepository) {
        this.modelMapper = modelMapper;
        this.beerRepository = beerRepository;
    }

    public Beer convertToEntity(BeerDTO beerDTO){
        return (beerDTO != null) ? modelMapper.map(beerDTO, Beer.class) : null;
    }

    public BeerDTO convertToDTO(Beer beer){
        return (beer != null) ? modelMapper.map(beer, BeerDTO.class) : null;
    }

    public BeerDTO findByName(String name){
        return Optional.ofNullable(beerRepository.findByName(name)).map(this::convertToDTO).orElseThrow(() -> new BirraNotFoundException("Birra "+name+" non disponibile!"));
    }

    public List<BeerDTO> findByBreweryName(String breweryName){
        return Optional.of(beerRepository.findByBrewery_Name(breweryName).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre del birrificio "+breweryName+" non disponibili!"));
    }

    public List<BeerDTO> findByBeerType(String beerType){
        return Optional.of(beerRepository.findByBeerType(BeerType.valueOf(beerType)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre della tipologia "+beerType+" non disponibili!"));
    }

    public List<BeerDTO> findByAvailable(boolean available){
        return Optional.of(beerRepository.findByAvailable(available).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre  non disponibili!"));
    }

    public List<BeerDTO> findByAvailableAndBrewery_Name(boolean available, String breweryName){
        return Optional.of(beerRepository.findByAvailableAndBrewery_Name(available, breweryName).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre del birrificio disponibili "+breweryName+" non trovate!"));
    }

    public List<BeerDTO> findByAvailableAndBeerType(boolean available, String beerType){
        return Optional.of(beerRepository.findByAvailableAndBeerType(available, BeerType.valueOf(beerType)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BirraNotFoundException("Birre disponibili del tipo "+beerType+" non trovate!"));
    }


}
