package com.specification.examples;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RequestSpecificationExample {
    static HashMap<String, Object> parameters = new HashMap<String, Object>();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";

        parameters.put("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")");
        parameters.put("format", "json");
        parameters.put("diagnostics", "true");
        parameters.put("env", "store://datatables.org/alltableswithkeys");
    }

    //1 Assert on count value
    @Test
    public void assertCountValue() {
        given()
                .parameters(parameters)
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we can assert the count to be returned to be equal to 5, using import static org.hamcrest.CoreMatchers.equalTo;
                .body("query.count", equalTo(5));
    }
}
