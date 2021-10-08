package com.ginogipsy.magicbus.component;

public interface StringUtility {

    boolean controlloCodiceFiscale(String codiceFiscale);
    String formattataMinuscConSpaziaturaCorretta(String string);
    String formattataMaiuscConSpaziaturaCorretta(String string);
    String formattazionePrimaMaiusc(String string);
    boolean capCorretto(String cap);

}
