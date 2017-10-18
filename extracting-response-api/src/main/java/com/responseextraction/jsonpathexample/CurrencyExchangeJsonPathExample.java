package com.responseextraction.jsonpathexample;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class CurrencyExchangeJsonPathExample {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
    }

    //1 Extract count from the response
    @Test
    public void extractCountFromResponse() {
        int count = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                .path("query.count");

        System.out.println("The value of count is: " + count);
    }

    //2 Extract lan value from the response
    @Test
    public void extractLangValueFromResponse() {
        String language = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .extract()
                .path("query.lang");

        System.out.println("The value of lang is: " + language);
    }

    //3 Extract 'Name': "USD/INR" from the first rate array
    @Test
    public void extractNameFromFirstArray() {
        String name = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate[0].Name");

        System.out.println("The value of the first name is: " + name);
    }

    //4 Get the values under rate
    @Test
    public void getRateValues(){
        List<String> rateValues = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate");

        System.out.println("The rate values are: " + rateValues);
        System.out.println("The size of rate is: " + rateValues.size());
    }

    //5 Get the size of rate
    @Test
    public void getSizeOfRate(){
        int sizeOfRate = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.size()");

        System.out.println("The size of rate is: " + sizeOfRate);
    }

    //6 Get all the names from the response
    @Test
    public void getAllNamesFromResponse(){
        List<String> allNamesFromResponse = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.Name");

        System.out.println("The list of all names is: " + allNamesFromResponse);
    }

    //7 Loop through the rate collection and pick the collection with Name="USD/BRL"
    //Get all values
    @Test
    public void getAllValuesInCollectionUSDBRL(){
        List<String> name = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.findAll{it.Name=='USD/BRL'}");

        System.out.println("The values for USD/BRL are: " + name);
    }

    //8 Get the rate value for  Name="USD/EUR"
    //Get all values
    @Test
    public void getRateValueForUSDEUR(){
        List<String> rateValues = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.findAll{it.Name=='USD/EUR'}.Rate");

        System.out.println("The rate value for USD/EUR is: " + rateValues);
    }

    //9 Get the Names which have Rate greater than 30
    @Test
    public void getNamesWithRateGreaterThan30(){
        Response response  = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql");

        //We can get the JsonPath from the Response - first case
        JsonPath path = response.jsonPath();

        //We can get the JsonPath from the Response - second case
        JsonPath path2 = new JsonPath(response.body().asInputStream());

        //We get a list of all Names, that have a rate greater than 30
        List<String> names = response.
                then()
                .extract()
                .path("query.results.rate.findAll{it.Rate > '30'}.Name");

        System.out.println("The names which have an exchange rate greater than 30 are: " + names);

        //Using the jsonPath to extract value from the path that comes from the response body
        System.out.println("First Path to get the only rate value: " + path.get("query.results.rate.findAll{it.Name=='USD/EUR'}.Rate"));

        //Using the jsonPath to extract value from the path2 that comes from the response body
        System.out.println("Second Path to get the rate as an array: " + path2.get("query.results.rate.findAll{it.Name=='USD/EUR'}"));

        //Printing in a nice way the JSON response
        System.out.println("Second Path to be printed nicely: " + path2.prettyPrint());
    }

    //10 Get all elements that start with id = USDB
    @Test
    public void getElementsThatStartsWithIdUsdb(){
        List<String> values = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.findAll{it.id==~/USDB.*/}");

        System.out.println("The value that start with USDB are: " + values);
    }

    //11 Get all elements that END with id = UD
    @Test
    public void getElementsThatEndsWithIdUd(){
        List<String> values = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "json")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                .extract()
                .path("query.results.rate.findAll{it.id==~/.*UD/}");

        System.out.println("The value that end with UD are: " + values);
    }
}
