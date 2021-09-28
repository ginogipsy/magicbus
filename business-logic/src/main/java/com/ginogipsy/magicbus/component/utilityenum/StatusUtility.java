package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.customexception.notfound.StatusProductsNotFoundException;

public interface StatusUtility {

    String statusVerify(final String status) throws StatusProductsNotFoundException;
}
