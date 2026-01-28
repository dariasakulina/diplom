package ru.iteco.fmhandroid.utils;

public final class TestData {

    private TestData() {}

    public static final String VALID_LOGIN = "login2";
    public static final String VALID_PASSWORD = "password2";
    public static final String NEWS_CATEGORY_ANNOUNCEMENT = "Объявление";
    public static final String NEWS_CATEGORY_BIRTHDAY = "День рождения";
    public static final String NEWS_DESCRIPTION_CREATE_PREFIX = "Описание новости: ";
    public static final String NEWS_TITLE_CREATE_PREFIX = "Новость: ";

    public static String uniqueDescription(String prefix) {
        return prefix + System.currentTimeMillis();
    }

    public static String uniqueTitle(String prefix) {
        return prefix + System.currentTimeMillis();
    }
}

