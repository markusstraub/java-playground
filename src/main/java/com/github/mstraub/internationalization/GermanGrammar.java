package com.github.mstraub.internationalization;

import java.util.Locale;

public class GermanGrammar implements Grammar {

    public static GermanGrammar INSTANCE = new GermanGrammar();

    private GermanGrammar() {
        // Exists only to thwart instantiation.
    }

    @Override
    public Locale getLocale() {
        return new Locale("de");
    }

    @Override
    public Gender getStreetGender(String street) {
        street = street.toLowerCase();
        if (street.endsWith("straße") || street.endsWith("strasse") || street.endsWith("gasse")
                || street.endsWith("allee"))
            return Gender.FEMININE;
        else if (street.endsWith("platz") || street.endsWith("steig"))
            return Gender.MASCULINE;
        else if (street.endsWith("gässchen") || street.endsWith("gasserl"))
            return Gender.NEUTER;

        return Gender.MASCULINE;
    }

}
