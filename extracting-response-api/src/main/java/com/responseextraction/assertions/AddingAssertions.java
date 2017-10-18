package com.responseextraction.assertions;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class AddingAssertions {
    //we can declare all parameters in a Hashmap object and reuse it in our calls, instead of repeating the code again
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

        System.out.println("Successfully asserted that query.count is equal to 5");
    }

    //2 Assert on Single Name
    @Test
    public void assertOnSingleName() {
        given()
                .parameters(parameters)
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //This will assert among all Names in the rate array is equal to "USD/INR"
                //note rate.Name will return a collection of objects. That's why we use hasItem method to check in each object in the collection
                .body("query.results.rate.Name", hasItem("USD/INR"));

        System.out.println("Successfully asserted that the collection rate has a Name equal to \"USD/INR\"");
    }

    //3 Assert on Multiple Names
    @Test
    public void assertOnMultipleNames() {
        given()
                .parameters(parameters)
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //This will assert among all Names in the rate array is equal to "USD/INR"
                //note rate.Name will return a collection of objects.
                //Here we can search for multiple names using hasItems
                .body("query.results.rate.Name", hasItems("USD/INR","USD/AUD","USD/BRL"));
    }

    //4 Assert using logical function
    @Test
    public void assertUsingLogicalValue() {
        given()
                .parameters(parameters)
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .body("query.count", greaterThan(4));

        given()
                .parameters(parameters)
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .body("query.count", lessThan(10));
    }

    //5 Adding multiple assertions
    @Test
    public void addMultipleAssertions() {
        given()
                .parameters(parameters)
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .body("query.results.rate.Name", hasItem("USD/INR"))
                .body("query.count", lessThan(10))
                .body("query.results.rate.Name", hasItems("USD/INR","USD/AUD","USD/BRL"));
    }
}
