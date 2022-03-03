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

@Slf4j
@Component
public class StringUtilityComponent implements StringUtility {

    @Override
    public boolean checkFiscalCode(String fiscalCode) {

        String regex = "^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$";
        return fiscalCode.matches(regex);
    }

    @Override
    public String formatAllMinusc(String string) {
        return Optional.ofNullable(string).map(n -> n.trim().toLowerCase().replaceAll(" +", " ")).orElse(null);
    }

    @Override
    public String formatAllMaiusc(String string) {
        return Optional.ofNullable(string).map(n -> n.trim().toUpperCase().replaceAll(" +", " ")).orElse(null);
    }

    @Override
    public String formatWithFirstMaiusc(final String string) {
        String[] parole = string.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String s : parole
        ) {
            s = s.substring(0, 1).toUpperCase() + formatAllMinusc(s.substring(1));
            result.append(s).append(" ");
        }
        return result.toString().trim();
    }

    @Override
    public boolean checkPostalCode(final String postalCode) {
        if (postalCode.length() != 5) {
            return false;
        }
        try {
            Integer.parseInt(postalCode);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    @Override
    public String convertListOfStringsJson(final String nameOfJsonObj, final List<String> listOfStrings) {
        log.info("Converting list of strings in json..");
        final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        final JsonObject test = new JsonObject();
        final JsonElement jsonData = gson.toJsonTree(listOfStrings, new TypeToken<List<String>>() {
        }.getType());
        test.add(nameOfJsonObj, jsonData);
        return gson.toJson(test);
    }
}


