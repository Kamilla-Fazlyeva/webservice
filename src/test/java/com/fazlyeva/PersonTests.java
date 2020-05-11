package com.fazlyeva;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static com.fazlyeva.enums.WebServiceConstants.Resource.*;
import static com.fazlyeva.utils.PropertiesLoader.getProperties;

public class PersonTests {

    @BeforeClass
    public void setUp() throws IOException {
        RestAssured.baseURI = getProperties().getProperty(BASE_URI.value);
        RestAssured.port = Integer.parseInt(getProperties().getProperty(PORT.value));
    }
}
