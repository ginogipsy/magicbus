package com.ginogipsy.magicbus.component.utilityenum;


import com.ginogipsy.magicbus.customexception.notfound.TipologiaMenuNotFoundException;

public interface TipologiaMenuUtility {

    String verifytipologiaMenu(String tipologiaMenu) throws TipologiaMenuNotFoundException;
}
