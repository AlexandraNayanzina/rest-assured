package com.rest;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.hasItem;

public class RequestSpecificationTest {
    Keys key = Keys.X_API_KEY;
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
         // given() and with() methods are identical
        requestSpecification = with()
                .baseUri("https://api.postman.com")
                .header("X-api-key", key.getKey())
                .header("Accept-Encoding", "gzip, deflate, br\n");

    }

    @Test
    public void validateStatusCodeTest() {
        given().spec(requestSpecification).
        when().
                get("/workspaces").
        then().
                assertThat().
                log().all().
                statusCode(200);

    }

    @Test
    public void validateResponseTest() {
        given().spec(requestSpecification)
                        .log().all().
        when()
                .get("/workspaces").
        then()
                .assertThat()
                .log().all()
                .body("workspaces.name", hasItem("My Workspace"));
    }

}
