package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.notfound.StatusProductsNotFoundException;
import com.ginogipsy.magicbus.domain.Status;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class StatusUtilityComponent implements StatusUtility {

    private final StringUtility stringUtility;

    public StatusUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public String statusVerify(String status) throws StatusProductsNotFoundException {
        return Arrays.stream(Status.values()).filter(status1 -> status1.toString().equals(stringUtility.formattataMaiuscConSpaziaturaCorretta(status))).findFirst().orElseThrow(() -> new StatusProductsNotFoundException("Base " + status + " non trovata!")).toString();

    }
}
