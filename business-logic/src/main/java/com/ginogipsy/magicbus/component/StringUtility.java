package com.ginogipsy.magicbus.component;

import java.util.List;

public interface StringUtility {

    boolean checkFiscalCode(String checkFiscalCode);

    String formatAllMinusc(String string);

    String formatAllMaiusc(String string);

    String formatWithFirstMaiusc(String string);

    boolean checkPostalCode(String postalCode);

    String convertListOfStringsJson(final String nameOfJsonObj, final List<String> listOfStrings);

}
