package com.student.test;

import com.student.base.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentInformation() {
        /**
         * given()
         * set cookies, add auth, add parameters, setting header info (all the initialization
         * .when()  --all actions are done after the when method
         * GET, POST, PUT, DELETE ...etc
         *.then() - we do validation on our response
         *Validate status code, extract response body, extract headers, cookies
         */

        Response response = given()
                .when()
                .get("/list");

//        System.out.println(response.body().prettyPrint());

        given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);
    }

    @Test
    public void getStudentInfo() {
        Response response = given()
                .when()
                .get("/1");

        System.out.println(response.body().prettyPrint());

        given().
                when()
                .get("/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void getStudentsFromFA(){
        Response response = given()
                .when()
                .get("/list?programme=Financial Analysis&limit=2");

//        System.out.println(response.prettyPeek());

        Response response2 = given()
                .param("programme", "Financial Analysis")
                .param("limit",2)
                .when()
                .get("/list");

        System.out.println(response2.prettyPeek());

    }
}
