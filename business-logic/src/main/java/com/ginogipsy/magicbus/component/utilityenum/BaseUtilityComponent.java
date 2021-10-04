package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.notfound.BaseNotFoundException;
import com.ginogipsy.magicbus.domain.Base;
import org.springframework.stereotype.Component;


import java.util.Arrays;


@Component
public class BaseUtilityComponent implements BaseUtility {

    private final StringUtility stringUtility;

    public BaseUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public String verifyBase(final String base) throws BaseNotFoundException {
        /*final String b = Optional.ofNullable(stringUtility.formattataMaiuscConSpaziaturaCorretta(base)).orElseThrow(() -> new BaseNotFoundException("Base risulta NULL!"));
        if(Arrays.stream(Base.values()).anyMatch(base1 -> base1.toString().equals(b))){
            return b;
        }else {
            throw new BaseNotFoundException("Base " + b + " non trovata!");
        }
        **FUNZIONANO ENTRAMBI -> sono la stessa cosa**
        */

        return Arrays.stream(Base.values()).filter(base1 -> base1.toString().equals(stringUtility.formattataMaiuscConSpaziaturaCorretta(base))).findFirst().orElseThrow(() -> new BaseNotFoundException("Base " + base + " non trovata!")).toString();
    }
}
