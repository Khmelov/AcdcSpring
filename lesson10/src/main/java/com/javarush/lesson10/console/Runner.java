package com.javarush.lesson10.console;

import java.util.Locale;

public class Runner {
    /**
     * @see <a href=https://youtu.be/vFKOXIiALNs?si=Is9IdITgSjUbn5MX>Video</a>
     * @param args
     */
    public static void main(String[] args) {
        ResourceManager translator = ResourceManager.INSTANCE;
        Locale locale = Locale.of("ru", "RU");
        translator.setLocale(locale);
        System.out.println(translator.get(Message.GREETING));
        System.out.println(translator.get(Message.QUESTION));
        System.out.println(translator.get(User.FIST_NAME));
        System.out.println(translator.get(User.LAST_NAME));
    }

}
