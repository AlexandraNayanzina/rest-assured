package com.rest.requestspecification;

import com.rest.Keys;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractSingleValueFromResponseUsingResponsePathTest {

    @Test
    public void extractSingleValueFromResponseUsingPathTest() {

        Keys key = Keys.X_API_KEY;

        String name = given()
                .baseUri("https://api.postman.com")
                .header("X-api-key", key.getKey())
                .header("Accept-Encoding", "gzip, deflate, br\n").
                when()
                .get("/workspaces").
                then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .path("workspaces[0].name");

        System.out.println("First workspace name: " + name);

    }
}
