package com.jsonpathexample;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

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

    //4
}
