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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;


public class PostRequestSendPayloadAsArrayListTest {
    Keys key = Keys.X_API_KEY;

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://1623b81a-ab10-403e-a861-f123939bc23b.mock.pstmn.io")
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

        HashMap <String, String> internalObj = new HashMap<String, String>();
        internalObj.put("id", "1");
        internalObj.put("type", "Chocolate");

        ArrayList<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
        jsonList.add(internalObj);

        Response response = with().
                body(jsonList)
                        .post("/post").then().extract().response();

    }

}
