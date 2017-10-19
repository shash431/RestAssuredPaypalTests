package com.specification;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class RequestSpecificationExample {

    //we need to set up the parameters, headers, cookies for each test, so instead of setting it up every time
    //we can use request specification requestBuilder in our @BeforeClass
    static RequestSpecBuilder builder;
    static RequestSpecification requestSpecification;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";

        builder = new RequestSpecBuilder();

        //adding all params
        builder.addParam("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")");
        builder.addParam("format", "json");
        builder.addParam("diagnostics", "true");
        builder.addParam("env", "store://datatables.org/alltableswithkeys");

        //using the requestBuilder to build and assign it to our request specification
        requestSpecification = builder.build();
    }

    //1 Assert status code using request specification
    @Test
    public void assertCountValue() {
        given()
                .spec(requestSpecification)
                //the resource we use is yql
                .get("/yql")
                .then()
                .statusCode(200);
    }
}
