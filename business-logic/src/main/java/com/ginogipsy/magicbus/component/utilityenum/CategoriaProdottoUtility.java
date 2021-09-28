package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.customexception.notfound.CategoriaProdottoNotFoundException;


public interface CategoriaProdottoUtility {

    String verifyCategoriaProdotto(String categoriaProdotto) throws CategoriaProdottoNotFoundException;
}
