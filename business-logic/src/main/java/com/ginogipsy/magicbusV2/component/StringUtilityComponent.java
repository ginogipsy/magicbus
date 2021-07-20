package com.ginogipsy.magicbusV2.component;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringUtilityComponent implements StringUtility {

    @Override
    public boolean controlloCodiceFiscale(String codiceFiscale) {

        String regex = "^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$";
        return codiceFiscale.matches(regex);
    }

    @Override
    public String formattataMinuscConSpaziaturaCorretta(String string) {
        return Optional.ofNullable(string).map(n -> n.trim().toLowerCase().replaceAll(" +", " ")).orElse(null);
    }

    @Override
    public String formattazionePrimaMaiusc(String string){
        String[] parole = string.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String s: parole
             ) {
            s = s.substring(0, 1).toUpperCase() + s.substring(1);
            result.append(s).append(" ");
        }
        return result.toString().trim();
    }
}


