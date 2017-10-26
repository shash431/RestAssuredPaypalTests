package com.paypalexamples.base;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;

import static com.jayway.restassured.RestAssured.given;

public class BaseClass {

    protected static String accessToken;
    private static final String clientID = "";
    private static final String clientSecret = "";

    @BeforeClass
    public static void init() {

        RestAssured.baseURI = "https://api.sandbox.paypal.com";
        RestAssured.basePath = "/v1";

        Response response = given()
                .param("grant_type", "client_credentials")
                .auth()
                .preemptive()
                .basic(clientID, clientSecret)
                .post("/oauth2/token");

        JsonPath jsonPath = response.jsonPath();
        accessToken = jsonPath.get("access_token");

        System.out.println("The token is: " + accessToken);
    }
}
