package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.notfound.TipologiaMenuNotFoundException;
import com.ginogipsy.magicbus.domain.TipologiaMenu;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class TipologiaMenuUtilityComponent implements TipologiaMenuUtility {

    private final StringUtility stringUtility;

    public TipologiaMenuUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public String verifytipologiaMenu(final String tipologiaMenu) throws TipologiaMenuNotFoundException {

        return Arrays.stream(TipologiaMenu.values())
                .filter(tipologiaMenu1 -> tipologiaMenu1.toString().equals(stringUtility.formattataMaiuscConSpaziaturaCorretta(tipologiaMenu)))
                .findFirst()
                .orElseThrow(() -> new TipologiaMenuNotFoundException("Base " + tipologiaMenu + " non trovata!"))
                .toString();

    }
}
