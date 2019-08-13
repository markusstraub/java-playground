package com.github.mstraub.internationalization;

import java.util.Locale;

public class EnglishGrammar implements Grammar {

    public static EnglishGrammar INSTANCE = new EnglishGrammar();

    private EnglishGrammar() {
        // Exists only to thwart instantiation.
    }

    @Override
    public Locale getLocale() {
        return new Locale("en");
    }

    @Override
    public Gender getStreetGender(String streetName) {
        return Gender.NONE;
    }

}
