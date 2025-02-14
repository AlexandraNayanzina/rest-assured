package com.rest.requestspecification;

import com.rest.Keys;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class QueryRequestSpecificationTest {
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

//        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
//        requestSpecBuilder.setBaseUri("https://api.postman.com");
//        requestSpecBuilder.addHeader("X-api-key", key.getKey());
//        requestSpecBuilder.log(LogDetail.ALL);
//
//        requestSpecification = requestSpecBuilder.build();

    }

    @Test
    public void queryRequestSpecification() {
//        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);

        System.out.println(queryableRequestSpecification.getBaseUri());
        System.out.println(queryableRequestSpecification.getHeaders());



    }

}
