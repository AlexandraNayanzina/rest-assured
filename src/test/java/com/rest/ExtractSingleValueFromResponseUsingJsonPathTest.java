package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractSingleValueFromResponseUsingJsonPathTest {

    @Test
    public void extractSingleValueFromResponseUsingPathTest() {

        Keys key = Keys.X_API_KEY;

        Response response = given()
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
                .response();

        JsonPath jsonPath = new JsonPath(response.asString());

        System.out.println("First workspace name: " + jsonPath.getString("workspaces[0].name"));

    }
}
