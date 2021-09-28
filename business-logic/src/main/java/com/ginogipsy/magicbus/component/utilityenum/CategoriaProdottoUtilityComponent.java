package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.notfound.CategoriaProdottoNotFoundException;
import com.ginogipsy.magicbus.domain.CategoriaProdotto;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Optional;

@Component
public class CategoriaProdottoUtilityComponent implements CategoriaProdottoUtility {

    private final StringUtility stringUtility;

    public CategoriaProdottoUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public String verifyCategoriaProdotto(final String categoriaProdotto) throws CategoriaProdottoNotFoundException {
        final String cp = Optional.ofNullable(stringUtility.formattataMaiuscConSpaziaturaCorretta(categoriaProdotto)).orElseThrow(() -> new CategoriaProdottoNotFoundException("Categoria prodotto risulta NULL!"));
        if(Arrays.stream(CategoriaProdotto.values()).anyMatch(categoria -> categoria.toString().equals(cp))){
            return cp;
        }else{
            throw new CategoriaProdottoNotFoundException("Categoria prodotto "+cp+" non trovata!");
        }
    }
}
