package com.fazlyeva.enums;

public class WebServiceConstants {

    public enum Resource {

        BASE_URI("URI"),
        PORT("port"),
        PATH_API("/api"),
        PATH_PERSON("/person/"),
        PATH_ADVERT("/advert/"),;

        public final String value;

        Resource(String value) {
            this.value = value;
        }
    }

    public enum Database {

        DB_DRIVER("db.Driver"),
        DB_URL("db.url"),
        DB_LOGIN("db.login"),
        DB_PASSWORD("db.password");

        public final String value;

        Database(String value) {
            this.value = value;
        }
    }

    public enum Fields {

        ID("id"),
        ID_PERSON("person_id"),
        NAME("name"),
        SURNAME("surname"),
        EMAIL("email"),
        CATEGORY("category"),
        HEADER("header"),
        BODY("body"),
        PHONE("phone");

        public final String value;

        Fields(String value) {
            this.value = value;
        }

    }

    public enum DatabaseQuery {

        SELECT_PERSON_ALL("select user_id, username, surname, email, " +
                "category from person order by user_id"),
        SELECT_PERSON_BY_ID("select user_id, username, surname, email, " +
                "category from person where user_id=?"),
        CREATE_PERSON("insert into person "
                + "(username, surname, email, category) values (?, ?, ?, ?)"),
        UPDATE_PERSON("update person set "
                + "username=?, surname=?, email=?, category=? where user_id=?"),
        DELETE_PERSON("delete from person where user_id=?"),


        SELECT_ADVERT_ALL("select * from advert order by advert_id"),
        SELECT_ADVERT_BY_PERSON_ID("select * from advert where person_id=?"),
        SELECT_ADVERT_BY_ID("select * from advert where advert_id=?"),
        CREATE_ADVERT("insert into advert "
                + "(person_id, header, body, category, phone, date) values (?, ?, ?, ?, ?, ?)"),
        UPDATE_ADVERT("update advert set "
                + "header=?, body=?, category=?, phone=? where advert_id=?"),
        DELETE_ADVERT("delete from advert where advert_id=?");

        public final String value;

        DatabaseQuery(String value) {
            this.value = value;
        }
    }

    public enum LoggerMessages {

        INFO_DB_DRIVER("PostgreSQL driver is loaded"),
        ERROR_DB_DRIVER("Specified class cannot be found"),
        INFO_DB_CONNECTION("Successful connection to PostgreSQL database"),
        ERROR_SQL("Connection to database is failed"),

        INFO_GET_PERSONS("All persons in the database are found"),
        INFO_GET_PERSON("Person by its id in database is found"),
        INFO_CREATE_PERSON("Person in the database is created"),
        INFO_UPDATE_PERSON("Person in the database is updated"),
        INFO_DELETE_PERSON("Person in the database is deleted"),

        INFO_GET_ADVERTS("All adverts in the database are found"),
        INFO_GET_PERSON_ADVERTS("All adverts by person's id in the database are found"),
        INFO_GET_ADVERT("Advert by its id in the database is found"),
        INFO_CREATE_ADVERT("Advert in the database is created"),
        INFO_UPDATE_ADVERT("Advert by its id in the database is updated"),
        INFO_DELETE_ADVERT("Advert by its id in the database is deleted");

        public final String value;

        LoggerMessages(String value) {
            this.value = value;
        }
    }
}
