package com.student.loggingexamples;

import com.student.base.TestBase;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoggingResponseValues extends TestBase {

    /**
     * This test will print out all Response Headers
     */
    @Test
    public void printResponseHeaders() {
        System.out.println("------------Printing Response Headers--------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .when()
                .get("/1")
                .then()
                .log()
                .headers()
                .statusCode(200);
    }

    /**
     * This test will print out all Response Status Line
     */
    @Test
    public void printStatusLine() {
        System.out.println("------------Printing Status Line--------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .when()
                .get("/1")
                .then()
                .log()
                .status()
                .statusCode(200);
    }

    /**
     * This test will print out all Response Body
     */
    @Test
    public void printResponseBody() {
        System.out.println("------------Printing Response Body--------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .when()
                .get("/1")
                .then()
                .log()
                .body()
                .statusCode(200);
    }

    /**
     * This test will print out all Response Body in case of Error
     */
    @Test
    public void printResponseBodyIfError() {
        System.out.println("------------Printing Response Body in case of Error--------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", -1)
                .when()
                .get("/list")
                .then()
                .log()
                .ifError();
    }
}

