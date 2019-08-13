package com.github.mstraub.internationalization;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18NSample {

    static public void main(String[] args) {
        String language;
        String country;

        if (args.length != 2) {
            language = "de";
            country = "AT";
        } else {
            language = new String(args[0]);
            country = new String(args[1]);
        }

        Locale currentLocale = new Locale(language, country);
        System.out.println(currentLocale);
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));

        Object[] messageArguments = { messages.getString("planet"), new Integer(7), new Date() };
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
        formatter.applyPattern(messages.getString("template"));
        System.out.println(formatter.format(messageArguments));

//        parse(null);
        parse("");
        parse("de-AT");
        parse("de");
        parse("DE");
        parse("DEU");
        parse("german");
        parse("germanxxxx");
        parse("krixikraxi");
    }

    static Locale parse(String languageTag) {
        Locale l = Locale.forLanguageTag(languageTag);
        System.out.println(languageTag + " can be mapped to a language? " + !l.getLanguage().isEmpty() + " [" + l.toLanguageTag() + "]");
        System.out.println("   language=" + l.getLanguage() + ", country=" + l.getCountry());
        System.out.println("   rfc1766 language tage: " + l.toLanguageTag());
        return l;
    }

}
