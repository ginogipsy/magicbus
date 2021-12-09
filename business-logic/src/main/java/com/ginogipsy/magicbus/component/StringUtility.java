package com.ginogipsy.magicbus.component;

public interface StringUtility {

    boolean checkFiscalCode(String checkFiscalCode);
    String formatAllMinusc(String string);
    String formatAllMaiusc(String string);
    String formatWithFirstMaiusc(String string);
    boolean checkPostalCode(String postalCode);

}
