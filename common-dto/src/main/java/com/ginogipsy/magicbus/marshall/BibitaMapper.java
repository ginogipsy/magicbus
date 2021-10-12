package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.customexception.notfound.BibitaNotFoundException;
import com.ginogipsy.magicbus.domain.Bibita;
import com.ginogipsy.magicbus.domain.Status;
import com.ginogipsy.magicbus.domain.TipologiaBibite;
import com.ginogipsy.magicbus.domain.TipologiaMenu;
import com.ginogipsy.magicbus.dto.BibitaDTO;
import com.ginogipsy.magicbus.repository.BibitaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BibitaMapper {

    private final ModelMapper modelMapper;
    private final BibitaRepository bibitaRepository;

    public BibitaMapper(ModelMapper modelMapper, BibitaRepository bibitaRepository) {
        this.modelMapper = modelMapper;
        this.bibitaRepository = bibitaRepository;
    }

    public Bibita convertToEntity(BibitaDTO bibitaDTO){
        return (bibitaDTO != null) ? modelMapper.map(bibitaDTO, Bibita.class) : null;
    }

    public BibitaDTO convertToDTO(Bibita bibita){
        return (bibita != null) ? modelMapper.map(bibita, BibitaDTO.class) : null;
    }

    public BibitaDTO findByNome(String nome){
        return Optional.ofNullable(bibitaRepository.findByNome(nome)).map(this::convertToDTO).orElseThrow(() -> new BibitaNotFoundException("Bibita "+nome+" non trovata!"));
    }

    List<BibitaDTO> findByTipologiaBibite(String tipologiaBibite){
        return Optional.of(bibitaRepository.findByTipologiaBibite(TipologiaBibite.valueOf(tipologiaBibite)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BibitaNotFoundException("Bibite per tipologia "+tipologiaBibite+" non trovato!"));
    }

    List<BibitaDTO> findByStatus(String status){
        return Optional.of(bibitaRepository.findByStatus(Status.valueOf(status)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BibitaNotFoundException("Bibite per "+status+" non trovato!"));
    }

    List<BibitaDTO> findByStatusAndTipologiaBibite(String status, String tipologiaBibite){
        return Optional.of(bibitaRepository.findByStatusAndTipologiaBibite(Status.valueOf(status), TipologiaBibite.valueOf(tipologiaBibite)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BibitaNotFoundException("Bibite per status "+status+" e tipologia "+tipologiaBibite+" non trovate!"));
    }

    List<BibitaDTO> findByStatusAndTipologiaMenu(String status, String tipologiaMenu){
        return Optional.of(bibitaRepository.findByStatusAndTipologiaMenu(Status.valueOf(status), TipologiaMenu.valueOf(tipologiaMenu)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new BibitaNotFoundException("Bibite per status "+status+" e tipologia "+tipologiaMenu+" non trovate!"));
    }
}
