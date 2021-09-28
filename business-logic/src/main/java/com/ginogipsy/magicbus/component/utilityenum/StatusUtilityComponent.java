package com.ginogipsy.magicbus.component.utilityenum;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.notfound.StatusProductsNotFoundException;
import com.ginogipsy.magicbus.domain.Status;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class StatusUtilityComponent implements StatusUtility {

    private final StringUtility stringUtility;

    public StatusUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public String statusVerify(String status) throws StatusProductsNotFoundException {
        final String s = Optional.ofNullable(stringUtility.formattataMaiuscConSpaziaturaCorretta(status)).orElseThrow(() -> new StatusProductsNotFoundException("Lo status risulta NULL!"));

        if((Arrays.stream(Status.values()).anyMatch(status1 -> status1.toString().equals(s)))){
            return s;
        }else{
            throw new StatusProductsNotFoundException("Status "+s+" non trovato!");
        }
    }
}
