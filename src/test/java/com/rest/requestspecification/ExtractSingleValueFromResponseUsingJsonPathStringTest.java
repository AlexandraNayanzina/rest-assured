package com.rest.requestspecification;

import com.rest.Keys;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractSingleValueFromResponseUsingJsonPathStringTest {

    @Test
    public void extractSingleValueFromResponseUsingPathTest() {

        Keys key = Keys.X_API_KEY;

        String response = given()
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
                .asString();

        String firstWorkspaseName = JsonPath.from(response).getString("workspaces[0].name");

        System.out.println("First workspace name: " + firstWorkspaseName);

    }
}
