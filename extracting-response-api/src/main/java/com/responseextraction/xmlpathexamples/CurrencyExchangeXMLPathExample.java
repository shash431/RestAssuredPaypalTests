package com.responseextraction.xmlpathexamples;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class CurrencyExchangeXMLPathExample {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
    }

    //1 Extract count from the response
    @Test
    public void extractCountFromResponse() {
        String count = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                .path("query.@yahoo:count");

        System.out.println("The value of count is: " + count);
    }

    //2 Extract language value from the response
    @Test
    public void extractLanguageFromResponse() {
        String lang = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                .path("query.@yahoo:lang");

        System.out.println("The value of count is: " + lang);
    }

    //3 Extract "Name": "USD/THB value from the first rate element
    @Test
    public void extractNameFromFirstRateElement() {
        String name = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                .path("query.results.rate[0].Name");

        System.out.println("The value of count is: " + name);
    }

    //4 Get the values under rate as String
    @Test
    public void getValuesUnderRateAsString() {
        String xml = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .andReturn()
                .asString();
        System.out.println("The value of xml is: " + xml);
    }

    //5 Get the size of rate
    @Test
    public void getSizeOfRate() {
        Object object = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                .path("query.results.rate");

        System.out.println("The class of the object is " + object.getClass());

        NodeChildrenImpl sizeOfNode = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                .path("query.results.rate");

        System.out.println("The the size of the object is: " + sizeOfNode.size());
    }

    //6 Get all the Names from the Response as String
    @Test
    public void getAllNamesFromResponse() {
        String names = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                //here we get all Name nodes value under all rate nodes
                .path("query.results.rate.findAll{it.Name}.Name")
                .toString();

        System.out.println("The value of xml is: " + names);
    }

    //7 Get all info for name USD/AUD
    @Test
    public void getAllInfoForGivenName() {
        String values = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                //here we return all data in the rate node with Name = 'USD/AUD'
                .path("query.results.rate.findAll{it.Name=='USD/AUD'}")
                .toString();

        System.out.println("All values for rate with Name == USD/AUD are: " + values);
    }

    //8 Extracting using attribute id="USDINR"
    @Test
    public void getRateByUsingIdAttribute() {
        String rate = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                //here we use it.@id because in the rate node we have an id attribute that we want to equal its value
                .path("query.results.rate.findAll{it.@id=='USDINR'}.Rate")
                .toString();

        System.out.println("All values for rate with Name == USD/AUD are: " + rate);
    }

    //9 Extracting using attribute id="USDINR" getting the Rate using ** the depth search
    @Test
    public void getRateByUsingIdAttributeAndDepthSearch() {
        String rate = given()
                .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDINR\", \"USDCAD\",\"USDAUD\",\"USDEUR\",\"USDBRL\")")
                .param("format", "xml")
                .param("diagnostics", "true")
                .param("env", "store://datatables.org/alltableswithkeys")
                .when()
                //the resource we use is yql
                .get("/yql")
                .then()
                //here we extract the path query.count that returns integer
                .extract()
                //here we use it.@id because in the rate node we have an id attribute that we want to equal its value
                .path("**.findAll{it.@id=='USDINR'}.Rate")
                .toString();

        System.out.println("All values for rate with Name == USD/AUD are: " + rate);
    }
}
