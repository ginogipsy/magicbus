package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.customexception.notfound.BaseNotFoundException;

public interface BaseUtility {

    String verifyBase(String base) throws BaseNotFoundException;
}
