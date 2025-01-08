package com.rest.requestspecification;

import com.rest.Keys;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class RequestSpecificationTest {
    Keys key = Keys.X_API_KEY;
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
         // given() and with() methods are identical
        requestSpecification = with()
                .baseUri("https://api.postman.com")
                .header("X-api-key", key.getKey())
                .header("Accept-Encoding", "gzip, deflate, br\n")
                .log().all();
    }

    @Test
    public void validateStatusCodeTest() {
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
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
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
        assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace"));

    }

}
