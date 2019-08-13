package com.github.mstraub.internationalization;

import java.util.Locale;

public class LocaleStrings {

    public static void main(String[] args) {
        print(new Locale("de-AT")); // wrong!
        print(new Locale("de", "AT"));
        print(Locale.forLanguageTag("de-AT"));
    }

    private static void print(Locale l) {
        System.out.println("language=" + l.getLanguage() + ", country=" + l.getCountry());
    }

}
