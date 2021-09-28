package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.notfound.TipologiaMenuNotFoundException;
import com.ginogipsy.magicbus.domain.TipologiaMenu;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class TipologiaMenuUtilityComponent implements TipologiaMenuUtility {

    private final StringUtility stringUtility;

    public TipologiaMenuUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public String verifytipologiaMenu(final String tipologiaMenu) throws TipologiaMenuNotFoundException {

        final String tm = Optional.ofNullable(stringUtility.formattataMaiuscConSpaziaturaCorretta(tipologiaMenu)).orElseThrow(() -> new TipologiaMenuNotFoundException("Tipologia menu risulta NULL!"));
        if(Arrays.stream(TipologiaMenu.values()).anyMatch(tipologia -> tipologia.toString().equals(tm))){
            return tm;
        }else {
            throw new TipologiaMenuNotFoundException("Tipologia menu " + tm + " non trovata!");
        }
    }
}
