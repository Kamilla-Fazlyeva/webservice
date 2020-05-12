package com.fazlyeva.simple_tests;

import com.fazlyeva.model.Person;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.fazlyeva.enums.TestData.PersonData.*;
import static com.fazlyeva.enums.WebServiceConstants.Fields.*;
import static com.fazlyeva.enums.WebServiceConstants.Resource.*;
import static com.fazlyeva.utils.PropertiesLoader.getProperties;
import static org.hamcrest.Matchers.*;

public class PersonTests {

    @BeforeMethod
    public void setUp() throws IOException {
        RestAssured.baseURI = getProperties().getProperty(BASE_URI.value);
        RestAssured.port = Integer.parseInt(getProperties().getProperty(PORT.value));
    }

    @Test
    public void getAllPersonsTest() {
        RestAssured
                .get(PATH_API.value + PATH_PERSON.value)
                .then()
                .log().everything()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Person[].class);
    }

    @Test
    public void getPersonByIdTest() {
        RestAssured
                .get(PATH_API.value + PATH_PERSON.value + PERSON_ID.value)
                .then()
                .log().everything()
                .statusCode(HttpStatus.SC_OK)
                .body(NAME.value, is(PERSON_NAME.value))
                .body(SURNAME.value, is(PERSON_SURNAME.value));
    }

    @Test
    public void postPersonTest() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PERSON_JSON.value)
                .when()
                .post(PATH_API.value + PATH_PERSON.value)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().everything();
    }

    @Test
    public void putPersonTest() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(UPDATE_PERSON_JSON.value)
                .when()
                .put(PATH_API.value + PATH_PERSON.value + 14)
                .then()
                .log().everything()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void deletePersonTest() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete(PATH_API.value + PATH_PERSON.value + 15)
                .then()
                .log().everything()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
