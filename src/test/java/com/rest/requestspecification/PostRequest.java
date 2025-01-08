package com.rest.requestspecification;

import com.rest.Keys;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PostRequest {
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

        String payload = "{\"workspace\":\n" +
                "    {\n" +
                "            \"name\": \"My First Workspace\",\n" +
                "            \"type\": \"personal\",\n" +
                "            \"description\": \"Rest Assured created it\"\n" +
                "        }\n" +
                "}";

        Response response = with().
                body(payload)
                        .post("/workspaces").then().extract().response();
        assertThat(response.<String>path("workspace.name"), equalTo("My First Workspace"));
        assertThat(response.<String>path("workspace.id"), matchesPattern("([A-Za-z0-9]+(-[A-Za-z0-9]+)+)"));

    }

}
