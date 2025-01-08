package com.rest.responsespecification;

import com.rest.Keys;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;


public class DefaultResponseSpecificationTest {
    Keys key = Keys.X_API_KEY;
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass() {

        requestSpecification = with()
                .baseUri("https://api.postman.com")
                .header("X-api-key", key.getKey())
                .header("Accept-Encoding", "gzip, deflate, br\n")
                .log().all();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecBuilder.build();

        RestAssured.responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void validateStatusCode() {
        given().spec(requestSpecification).
        when()
                .get("/workspaces");

    }

}
