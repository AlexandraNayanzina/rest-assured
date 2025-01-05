package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;


public class AssertResponseBodyTest {

    @Test
    public void assertResponseBodyTest() {

        Keys key = Keys.X_API_KEY;

        given()
                .baseUri("https://api.postman.com")
                .header("X-api-key", key.getKey())
                .header("Accept-Encoding", "gzip, deflate, br\n").
                when()
                .get("/workspaces").
                then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name", hasItems("My Workspace",
                        "Team Workspace",
                        "LearnAPITesting"))
                .body("workspaces.type", hasItems("personal",
                        "team",
                        "team"))
                .body("workspaces.name", hasItem("My Workspace"))
                .body("workspaces[0].name", equalTo("My Workspace"))
                .body("workspaces[0].type", equalTo("personal"))
                .body("workspaces.size()", equalTo(3));


    }


}
