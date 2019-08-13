package com.github.mstraub.internationalization;

import java.util.Locale;

/**
 * This class encapsulates i18n details that can not be solved through
 * translation files, e.g. determine the grammatical gender.
 * <a href="https://en.wikipedia.org/wiki/Grammatical_gender"/>
 */
public interface Grammar {

    public enum Gender {
        /** for languages not differentiating gender */
        NONE(""), MASCULINE("_m"), FEMININE("_f"), NEUTER("_n");

        private String appendix;

        private Gender(String appendix) {
            this.appendix = appendix;
        }

        /**
         * @return appendix for the translation identifier strings
         */
        public String getAppendix() {
            return appendix;
        }
    };

    Locale getLocale();

    Gender getStreetGender(String streetName);

    /**
     * @return a new instance of a matching {@link Grammar}
     */
    static Grammar create(Locale l) {
        if (l.getLanguage().equals(new Locale("en").getLanguage()))
            return EnglishGrammar.INSTANCE;
        else if (l.getLanguage().equals(new Locale("de").getLanguage()))
            return GermanGrammar.INSTANCE;
        throw new UnsupportedOperationException("language " + l + " not yet supported");
    }

}
