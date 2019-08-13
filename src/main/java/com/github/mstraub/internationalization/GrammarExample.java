package com.github.mstraub.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class GrammarExample {

    private static I18NCache i18nCache = new I18NCache("MessagesBundle");

    static public void main(String[] args) {
        print(new Locale("en", "US"));
        print(new Locale("de", "DE"));
        print(new Locale("pl"));
    }

    private static void print(Locale locale) {
        System.out.println(locale);
        
        ResourceBundle messages = i18nCache.getResourceBundle(locale);
        Grammar grammar = i18nCache.getGrammar(locale);

        String[] streets = new String[] { "Kirchenstraße", "Kirchengasserl", "Kirchenweg", "Kirchenplatz",
                "Kirchenallee" };
        for (String streetName : streets) {
            String key = "turn" + grammar.getStreetGender(streetName).getAppendix();
            System.out.println(i18nCache.getMessageFormat(grammar, key)
                    .format(new Object[] { messages.getString("left"), streetName }));
        }
    }

}
