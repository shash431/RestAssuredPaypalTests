package com.checkresponsetime;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class VerifyingTime {

    //we need to set up the parameters, headers, cookies for each test, so instead of setting it up every time
    //we can use request specification requestBuilder in our @BeforeClass
    private static RequestSpecBuilder requestBuilder;
    private static RequestSpecification requestSpecification;

    //This is our response request requestBuilder
    //It is used if we want to set the same set of conditions for every single response
    //for example log().all().statusCode(200)
    //or you can check some stuff in the headers is there
    //or in our case that the count is always 5
    private static ResponseSpecBuilder responseBuilder;
    private static ResponseSpecification responseSpecification;

    //Map for headers
    static Map<String, Object> responseHeaders = new HashMap<String, Object>();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";

        requestBuilder = new RequestSpecBuilder();

        //adding all params
        requestBuilder.addParam("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")");
        requestBuilder.addParam("format", "json");
        requestBuilder.addParam("diagnostics", "true");
        requestBuilder.addParam("env", "store://datatables.org/alltableswithkeys");

        //using the requestBuilder to build and assign it to our request specification
        requestSpecification = requestBuilder.build();


        //BUILDING RESPONSE
        //building the response headers
        responseHeaders.put("Content-Type", "application/json;charset=utf-8");
        responseHeaders.put("Server", "ATS");

        responseBuilder = new ResponseSpecBuilder();
        //we expect the count returned always to be 5
        responseBuilder.expectBody("query.count", equalTo(5));
        //we expect the status code returned is always 200
        responseBuilder.expectStatusCode(200);
        //expect the headers that we build in our responseHeaders using expectHeaders
        responseBuilder.expectHeaders(responseHeaders);

        //we can add a method to the responseBuilder that expects the response time not to be greater than 5 seconds
        //if it is then the test will fail
        responseBuilder.expectResponseTime(lessThan(0L), TimeUnit.SECONDS);

        //we add our responseBuilder to our responseSpec
        responseSpecification = responseBuilder.build();
    }

    //1 Get the time taken for the response
    //we can use this to assert if the time to response is less than a certain amount of time
    @Test
    public void assertCountValue() {
        long responseTime =
                given()
                        .spec(requestSpecification)
                        .log()
                        .all()
                        .get("/yql")
                        .timeIn(TimeUnit.SECONDS);

        System.out.println("The response time is: " + responseTime + " seconds");

        given()
                .spec(requestSpecification)
                .log()
                .all()
                .when()
                .get("/yql")
                .then()
                .time(lessThan(5L), TimeUnit.SECONDS);

        given()
                .spec(requestSpecification)
                .log()
                .all()
                .when()
                .get("/yql")
                .then()
                .spec(responseSpecification);
    }
}
