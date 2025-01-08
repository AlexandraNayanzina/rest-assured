package com.rest.requestspecification;

import com.rest.Keys;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractResponseTest {

    @Test
    public void assertResponseBodyTest() {

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

        System.out.println("Response: " + response.asString());



    }


}

