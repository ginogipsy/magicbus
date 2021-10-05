package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.notfound.CategoriaProdottoNotFoundException;
import com.ginogipsy.magicbus.domain.CategoriaProdotto;
import org.springframework.stereotype.Component;


import java.util.Arrays;


@Component
public class CategoriaProdottoUtilityComponent implements CategoriaProdottoUtility {

    private final StringUtility stringUtility;

    public CategoriaProdottoUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public String verifyCategoriaProdotto(final String categoriaProdotto) throws CategoriaProdottoNotFoundException {
        return Arrays.stream(CategoriaProdotto.values())
                .filter(categoriaProdotto1 -> categoriaProdotto1.toString().equals(stringUtility.formattataMaiuscConSpaziaturaCorretta(categoriaProdotto)))
                .findFirst()
                .orElseThrow(() -> new CategoriaProdottoNotFoundException("Base " + categoriaProdotto + " non trovata!"))
                .toString();

    }
}
