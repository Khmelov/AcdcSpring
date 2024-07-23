package com.javarush.lesson10.console;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {
    INSTANCE;

    public static final String BASENAME = "lang";
    private ResourceBundle resourceBundle;

    ResourceManager() {
        setLocale(Locale.getDefault());
    }

    public void setLocale(Locale locale) {
        this.resourceBundle = ResourceBundle.getBundle(BASENAME, locale);
    }

    public String get(String key) {
        return resourceBundle.getString(key);
    }
}
