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

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;


public class PostRequestSendPayloadAsObjectTest {
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
    public void createPostmanWorkspace() {

        HashMap <String, Object> payload = new HashMap<String, Object>();
        HashMap <String, String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name", "My Fourth Workspace");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "Rest Assured created it - Payload is an Object");
        payload.put("workspace", nestedObject);

        Response response = with().
                body(payload)
                        .post("/workspaces").then().extract().response();
        assertThat(response.<String>path("workspace.name"), equalTo("My Fourth Workspace"));
        assertThat(response.<String>path("workspace.id"), matchesPattern("([A-Za-z0-9]+(-[A-Za-z0-9]+)+)"));

    }

}
