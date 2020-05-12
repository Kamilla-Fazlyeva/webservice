package com.fazlyeva.simple_tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.fazlyeva.enums.TestData.AdvertData.*;
import static com.fazlyeva.enums.TestData.PersonData.*;
import static com.fazlyeva.enums.WebServiceConstants.Fields.*;
import static com.fazlyeva.enums.WebServiceConstants.Resource.*;
import static com.fazlyeva.utils.PropertiesLoader.getProperties;
import static org.hamcrest.Matchers.is;

public class AdvertTests {

    @BeforeMethod
    public void setUp() throws IOException {
        RestAssured.baseURI = getProperties().getProperty(BASE_URI.value);
        RestAssured.port = Integer.parseInt(getProperties().getProperty(PORT.value));
    }

    @Test
    public void getAllAdverts() {
        RestAssured
                .get(PATH_API.value + PATH_ADVERT.value)
                .then()
                .log().everything()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
    }

    @Test
    public void getAdvertByIdTest() {
        RestAssured
                .get(PATH_API.value + PATH_ADVERT.value + ADVERT_ID.value)
                .then()
                .log().everything()
                .statusCode(HttpStatus.SC_OK)
                .body(ID_PERSON.value, is(Integer.parseInt(ADVERT_PERSON_ID.value)))
                .body(HEADER.value, is(ADVERT_HEADER.value));
    }

    @Test
    public void getAdvertsByPersonIdTest() {
        RestAssured
                .get(PATH_API.value + PATH_PERSON.value + PERSON_ID.value + PATH_ADVERT.value)
                .then()
                .log().everything()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
    }

    @Test
    public void postAdvertTest() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(ADVERT_JSON.value)
                .when()
                .post(PATH_API.value + PATH_ADVERT.value)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().everything();
    }


    @Test
    public void putAdvertTest() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UPDATE_ADVERT_JSON.value)
                .when()
                .put(PATH_API.value + PATH_ADVERT.value + 8)
                .then()
                .log().everything()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }


    @Test
    public void deleteAdvertTest() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete(PATH_API.value + PATH_ADVERT.value + 8)
                .then()
                .log().everything()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
