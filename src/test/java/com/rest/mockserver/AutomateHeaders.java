package com.rest.mockserver;

import io.restassured.http.Header;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class AutomateHeaders {

    Header header = new Header("headerName2", "value2");
    Header matchHeader = new Header("x-mock-match-request-headers", "headerName2");


    @Test
    public void multipleHeadersTest() {

        given()
                .baseUri("https://1623b81a-ab10-403e-a861-f123939bc23b.mock.pstmn.io")
                .header(header)
                .header(matchHeader)
                .log().all().
                when()
                .get("/get").
                then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .body("msg", equalTo("responseValue2"));
    }
}
