package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.notfound.PeriodoDisponibilitaNotFoundException;
import com.ginogipsy.magicbus.domain.PeriodoDisponibilita;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class PeriodoDisponibilitaUtilityComponent implements PeriodoDisponibilitaUtility {

    private final StringUtility stringUtility;

    public PeriodoDisponibilitaUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public String verifyPeriodoDisponibilita(final String periodoDisponibilita) throws PeriodoDisponibilitaNotFoundException {
        final String pd = Optional.ofNullable(stringUtility.formattataMaiuscConSpaziaturaCorretta(periodoDisponibilita)).orElseThrow(() -> new PeriodoDisponibilitaNotFoundException("Periodo disponibilità risulta NULL!"));
        if(Arrays.stream(PeriodoDisponibilita.values()).anyMatch(pDisponibilita -> pDisponibilita.toString().equals(pd))){
            return pd;
        }else{
            throw new PeriodoDisponibilitaNotFoundException("Periodo disponibilità "+pd+" non trovato!");
        }
    }
}
