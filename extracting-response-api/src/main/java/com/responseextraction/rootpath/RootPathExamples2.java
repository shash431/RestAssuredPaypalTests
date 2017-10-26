package com.responseextraction.rootpath;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class RootPathExamples2 {

    //we can declare all parameters in a Hashmap object and reuse it in our calls, instead of repeating the code again
    static HashMap<String, Object> parameters = new HashMap<String, Object>();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
        //Here we can provide the base Root Path if it will be the same for all tests
        RestAssured.rootPath = "query.results.rate";


        parameters.put("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")");
        parameters.put("format", "json");
        parameters.put("diagnostics", "true");
        parameters.put("env", "store://datatables.org/alltableswithkeys");
    }

    //1 Adding multiple assertions in single test
    @Test
    public void addMultipleAssertions() {
        given()
                .parameters(parameters)
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()

                .root("query.results.rate")
                .body("Name", hasItem("USD/INR"))
                .body("Name", hasItems("USD/INR","USD/AUD","USD/BRL"))
                .body("id",hasItems("USDCAD"))

                .root("query")
                .body("count", lessThan(10))
                .body("count", greaterThan(4));
    }

    //2 Assert on Multiple Names
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
                .body("Name", hasItems("USD/INR","USD/AUD","USD/BRL"));
    }

    @Test
    public void verifyIdHasItemUSDCAD() {
        given()
                .parameters(parameters)
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .body("id",hasItems("USDCAD"));
    }


    //here after each test we can reset the RestAssured data to be empty
    @AfterClass
    public static void tearDown(){
        RestAssured.reset();
    }
}
