package com.rest.requestspecification;

import com.rest.Keys;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;


public class PutRequestBddStyle {
    Keys key = Keys.X_API_KEY;

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com/")
                .addHeader("x-api-key", key.getKey())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .expectContentType(ContentType.JSON);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void createPostmanWorspace() {
        String workspaceId = "148a6cc8-4543-43d1-9619-d0f954cb6fe4";

        String payload = "{\"workspace\":\n" +
                "    {\n" +
                "            \"name\": \"My First Workspace UPDATED3\",\n" +
                "            \"type\": \"personal\",\n" +
                "            \"description\": \"Rest Assured created it\"\n" +
                "        }\n" +
                "}";

        given()
                .body(payload).
        when()
                .put("/workspaces/" + workspaceId).
        then()
                .assertThat()
                .log().all()
                .body("workspace.name", equalTo("My First Workspace UPDATED3"))
                .body("workspace.id", equalTo(workspaceId));


    }

}
