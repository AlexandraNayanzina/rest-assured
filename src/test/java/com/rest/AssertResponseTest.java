package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class AssertResponseTest {

    @Test
    public void assertResponseTest() {
        Keys key = Keys.X_API_KEY;
        given().
                baseUri("https://api.postman.com").
                header("X-api-key", key.getKey()).
                header("Accept-Encoding" ,"gzip, deflate, br\n").
        when().
                get("/workspaces").
        then().
                assertThat().
                log().all().
                statusCode(200);

    }
}
