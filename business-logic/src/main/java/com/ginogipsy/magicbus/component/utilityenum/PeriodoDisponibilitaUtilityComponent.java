package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.notfound.PeriodoDisponibilitaNotFoundException;
import com.ginogipsy.magicbus.domain.PeriodoDisponibilita;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class PeriodoDisponibilitaUtilityComponent implements PeriodoDisponibilitaUtility {

    private final StringUtility stringUtility;

    public PeriodoDisponibilitaUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public String verifyPeriodoDisponibilita(final String periodoDisponibilita) throws PeriodoDisponibilitaNotFoundException {
        return Arrays.stream(PeriodoDisponibilita.values())
                .filter(periodoDisponibilita1 -> periodoDisponibilita1.toString().equals(stringUtility.formattataMaiuscConSpaziaturaCorretta(periodoDisponibilita)))
                .findFirst()
                .orElseThrow(() -> new PeriodoDisponibilitaNotFoundException("Base " + periodoDisponibilita + " non trovata!"))
                .toString();

    }
}
