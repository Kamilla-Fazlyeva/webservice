package com.fazlyeva.enums;

public class TestData {

    public enum PersonData {

        PERSON_ID("1"),
        PERSON_NAME("Ivan"),
        PERSON_SURNAME("Ivanov"),
        PERSON_EMAIL("ivanov@mail.ru"),
        PERSON_CATEGORY("test"),

        PERSON_JSON("{\"name\":\"Petr\",\"surname\":\"Petrov\"," +
                "\"email\":\"petrov@mail.ru\",\"category\":\"test\"}"),
        UPDATE_PERSON_JSON("{\"id\": 14, \"name\":\"Petr\",\"surname\":\"Petrov\"," +
                "\"email\":\"petrov1@mail.ru\",\"category\":\"test1\"}");

        public final String value;

        PersonData(String value) {
            this.value = value;
        }
    }

    public enum AdvertData {

        ADVERT_ID("3"),
        ADVERT_PERSON_ID("1"),
        ADVERT_HEADER("header"),
        ADVERT_BODY("body"),
        ADVERT_CATEGORY("category"),
        ADVERT_PHONE("+79456984659"),

        ADVERT_JSON("{\"person_id\": 6,\"header\": \"header\",\"body\": \"body\"," +
                "\"category\": \"test\",\"phone\": \"+7945698459\"}"),
        UPDATE_ADVERT_JSON("{\"id\": 8,\"person_id\": 6,\"header\": \"header\",\"body\": \"body\"," +
                "\"category\": \"test1\",\"phone\": \"+7945698459\"}");

        public final String value;

        AdvertData(String value) {
            this.value = value;
        }
    }
}
