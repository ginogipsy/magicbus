package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Beer;
import com.ginogipsy.magicbus.domain.enums.BeerTypeEnum;
import com.ginogipsy.magicbus.dto.BeerDTO;
import com.ginogipsy.magicbus.repository.BeerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class BeerMapper {

    private final ModelMapper modelMapper;
    private final BeerRepository beerRepository;

    public BeerMapper(ModelMapper modelMapper, BeerRepository beerRepository) {
        this.modelMapper = modelMapper;
        this.beerRepository = beerRepository;
    }

    public Beer convertToEntity(final BeerDTO beerDTO) {
        return Optional.ofNullable(beerDTO)
                .map(b -> modelMapper.map(b, Beer.class))
                .orElse(null);
    }

    public BeerDTO convertToDTO(final Beer beer) {
        return Optional.ofNullable(beer)
                .map(b -> modelMapper.map(b, BeerDTO.class))
                .orElse(null);
    }

    public BeerDTO findByName(final String name) {
        log.info("Searching beer where name is " + name+ "..");
        return Optional.ofNullable(name)
                .map(beerRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<BeerDTO> findByBreweryName(final String breweryName) {
        log.info("Searching beers where breweryName is " + breweryName+ "..");
        return Optional.ofNullable(breweryName)
                .map(bn -> beerRepository.findByBrewery_Name(bn)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<BeerDTO> findByBeerType(final String beerType) {
        log.info("Searching beers where beerType is " + beerType+ "..");
        return Optional.ofNullable(beerType)
                .map(bt -> beerRepository.findByBeerType(BeerTypeEnum.valueOf(bt))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<BeerDTO> findByAvailable(final boolean available) {
        log.info("Searching list of beers where availability is " + available+ "..");
        return beerRepository.findByAvailable(available)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<BeerDTO> findByAvailableAndBrewery_Name(final boolean available, final String breweryName) {
        log.info("Searching list of beers where availability is " + available+ " and breweryName is "+breweryName+"..");
        return Optional.ofNullable(breweryName)
                .map(bw -> beerRepository.findByAvailableAndBrewery_Name(available, bw)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<BeerDTO> findByAvailableAndBeerType(final boolean available, final String beerType) {
        log.info("Searching list of beer where availability is " + available+ " and beerType is "+beerType+"..");
        return Optional.ofNullable(beerType)
                .map(bt -> beerRepository.findByAvailableAndBeerType(available, BeerTypeEnum.valueOf(bt))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
