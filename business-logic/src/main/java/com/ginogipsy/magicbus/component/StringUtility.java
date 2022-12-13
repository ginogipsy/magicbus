package com.ginogipsy.magicbus.component;

import java.util.List;

/**
 * @author ginogipsy
 */

public interface StringUtility {

    boolean checkFiscalCode(final String checkFiscalCode);

    String formatAllLower(final String string);

    String formatAllUpper(final String string);

    String formatWithFirstUpper(final String string);

    boolean checkPostalCode(final String postalCode);

    String convertListOfStringsJson(final String nameOfJsonObj, final List<String> listOfStrings);

}
