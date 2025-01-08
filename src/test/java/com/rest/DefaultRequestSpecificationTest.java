package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class DefaultRequestSpecificationTest {
    Keys key = Keys.X_API_KEY;
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
         // given() and with() methods are identical
//        requestSpecification = with()
//                .baseUri("https://api.postman.com")
//                .header("X-api-key", key.getKey())
//                .header("Accept-Encoding", "gzip, deflate, br\n")
//                .log().all();

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-api-key", key.getKey());
        requestSpecBuilder.log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

    }

    @Test
    public void validateStatusCodeTest() {
        Response response = given(requestSpecification).get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        given().spec(requestSpecification).
        when().
                get("/workspaces").
        then().
                assertThat().
                log().all().
                statusCode(200);

    }

}
