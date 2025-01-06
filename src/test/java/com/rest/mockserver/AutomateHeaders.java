package com.rest.mockserver;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.List;

public class AutomateHeaders {

    @Test
    public void multipleHeadersTest() {

        Header header = new Header("headerName2", "value2");
        Header matchHeader = new Header("x-mock-match-request-headers", "headerName2");

        Headers headers = new Headers(header, matchHeader);

        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("headerName2", "value2");
        headersMap.put("x-mock-match-request-headers", "headerName2");


        Headers resHeaders = given()
                .baseUri("https://1623b81a-ab10-403e-a861-f123939bc23b.mock.pstmn.io")
                .headers(headersMap)
                .log().all().
       when()
                .get("/get").
       then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .body("msg", equalTo("responseValue2"))
                .header("responseHeader2", "resValue2")
                .extract().headers();

        System.out.println(resHeaders);

        List<String> multipleHeaderValue = resHeaders.getValues("multiValueHeader");
        for (String value: multipleHeaderValue){
            System.out.println(value);
        }
    }
}
