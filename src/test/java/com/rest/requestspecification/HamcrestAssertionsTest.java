package com.rest.requestspecification;

import com.rest.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestAssertionsTest {

    @Test
    public void hamcrestAssertionsTest() {

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

        // Hamcrest assertions
        assertThat(name, equalTo("My Workspace"));

        //TestNG assertion
        Assert.assertEquals(name, "My Workspace");
        System.out.println("First workspace name: " + name);

    }

    @Test
    public void hamcrestAssertionsTest2() {
        // The difference between the hasItems() and contacins() assertions.
        // hasItems() soft assertion, checking if the elements are in the collection
        // contains(), strict assertion, checking the exact order and elements in the collection
        // containsInAnyOrder() soft assertion, does NOT consider the order of the elements
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
                .body("workspaces.name",  contains("My Workspace", "Team Workspace", "LearnAPITesting"))
                .body("workspaces.name",  containsInAnyOrder("Team Workspace", "My Workspace", "LearnAPITesting"))
                .body("workspaces.name", is(not(empty())),
                        "workspaces.name", hasSize(3),
                        "workspaces[0]", hasKey("id"),
                        "workspaces[0]", hasValue("personal"),
                        "workspaces[0]", hasEntry("type", "personal"),
                        "workspaces[0].name", allOf(startsWith("My"), containsString("space"))
                );

    }
}
