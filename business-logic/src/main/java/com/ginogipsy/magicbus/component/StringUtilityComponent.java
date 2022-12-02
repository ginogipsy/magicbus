package com.ginogipsy.magicbus.component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
public class StringUtilityComponent implements StringUtility {

    @Override
    public boolean checkFiscalCode(final String fiscalCode) {
        log.info("StringUtilityComponent - checkFiscalCode() -> check if this fiscal code {} is correct", fiscalCode);
        String regex = "^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$";
        return fiscalCode.matches(regex);
    }

    @Override
    public String formatAllLower(final String string) {
        log.info("StringUtilityComponent - formatAllLower() -> format '{} to lower ..' ", string);
        return Optional.ofNullable(string)
                .map(n -> n.trim().toLowerCase().replaceAll(" +", " "))
                .orElse(null);
    }

    @Override
    public String formatAllUpper(final String string) {
        log.info("StringUtilityComponent - formatAllUpper() -> format '{}' to UPPER .. ", string);
        return Optional.ofNullable(string)
                .map(n -> n.trim().toUpperCase().replaceAll(" +", " "))
                .orElse(null);
    }

    @Override
    public String formatWithFirstUpper(final String string) {
        log.info("StringUtilityComponent - formatWithFirstUpper() -> format '{}' with first upper.. ", string);
        String[] parole = string.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String s : parole
        ) {
            s = s.substring(0, 1).toUpperCase() + formatAllLower(s.substring(1));
            result.append(s).append(" ");
        }
        return result.toString().trim();
    }

    @Override
    public boolean checkPostalCode(final String postalCode) {
        log.info("StringUtilityComponent - checkPostalCode() -> check if this postal code {} is correct.. ", postalCode);
        if (postalCode.length() != 5) {
            log.warn("StringUtilityComponent - checkPostalCode() -> this postal code {} is incorrect!", postalCode);
            return false;
        }
        try {
            Integer.parseInt(postalCode);
        } catch (RuntimeException e) {
            log.warn("StringUtilityComponent - checkPostalCode() -> this postal code {} is incorrect!", postalCode);
            return false;
        }
        return true;
    }

    @Override
    public String convertListOfStringsJson(final String nameOfJsonObj, final List<String> listOfStrings) {
        log.info("StringUtilityComponent - convertListOfStringsJson() -> Converting list of strings in json..");
        final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        final JsonObject test = new JsonObject();
        final JsonElement jsonData = gson.toJsonTree(listOfStrings, new TypeToken<List<String>>() {
        }.getType());
        test.add(nameOfJsonObj, jsonData);
        return gson.toJson(test);
    }
}


