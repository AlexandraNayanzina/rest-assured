package com.rest.requestspecification;

import com.rest.Keys;
import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class LoggingTest {

    @Test
    public void assertResponseTest() {
        Keys key = Keys.X_API_KEY;
        given()
                .baseUri("https://api.postman.com")
                .header("X-api-key", key.getKey())
                .header("Accept-Encoding" ,"gzip, deflate, br\n").
                config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-api-key"))).
                log().all().

        when().
                get("/workspaces").

        then().
                assertThat().
              //  log().ifError().
                statusCode(201);

    }
}
