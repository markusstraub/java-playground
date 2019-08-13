package com.github.mstraub.internationalization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class I18NCache {

    private String resourceBundleName;
    private Table<Grammar, String, MessageFormat> cache = HashBasedTable.create();

    public I18NCache(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    public MessageFormat getMessageFormat(Grammar grammar, String key) {
        if (!cache.contains(grammar, key)) {
            MessageFormat formatter = new MessageFormat("");
            formatter.setLocale(grammar.getLocale());
            formatter.applyPattern(getResourceBundle(grammar.getLocale()).getString(key));
            cache.put(grammar, key, formatter);
        }
        return cache.get(grammar, key);
    }

    public Grammar getGrammar(Locale locale) {
        return Grammar.create(locale);
    }

    public ResourceBundle getResourceBundle(Locale locale) {
        // bundles are cached automatically
        return ResourceBundle.getBundle(resourceBundleName, locale);
    }

}
