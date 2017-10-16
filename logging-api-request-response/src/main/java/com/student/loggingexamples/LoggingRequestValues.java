package com.student.loggingexamples;

import com.jayway.restassured.http.ContentType;
import com.student.base.TestBase;
import com.student.model.Student;
import org.junit.Test;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;

public class LoggingRequestValues extends TestBase {

    /**
     * This test will print out all Request Headers
     */
    @Test
    public void printRequestHeaders() {
        System.out.println("------------Printing Request Headers--------------");
        //to print the log, we need to have log() method after the given()
        //we can print cookie, header
        given()
                .log()
                .headers()
                .when()
                .get("/1")
                .then()
                .statusCode(200);
    }

    /**
     * This test will print out all Request Parameters
     */
    @Test
    public void printRequestParameters() {
        System.out.println("------------Printing Request Parameters--------------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .log()
                .params()
                .when()
                .get("/1")
                .then()
                .statusCode(200);
    }

    /**
     * This test will print out all Request Body
     */
    @Test
    public void printRequestBody() {
        System.out.println("------------Printing Request Body--------------");
        //email address need to be unique
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        Student student = new Student();
        student.setFirstName("Donyo");
        student.setLastName("Donev");
        student.setEmail("test@donev.com2");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .log()
                .body()
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);
    }

    /**
     * This test will print out all Request Details
     */
    @Test
    public void printRequestAllDetails() {
        System.out.println("------------Printing Request Details--------------");
        //email address need to be unique
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        Student student = new Student();
        student.setFirstName("Donyo");
        student.setLastName("Donev");
        student.setEmail("test@donev.com2");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);
    }

    /**
     * This test will print out all Request Details if validation fails
     */
    @Test
    public void printRequestAllDetailsIfValidationFails() {
        System.out.println("------------Printing Request Details If Validation Fails--------------");
        //email address need to be unique
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        Student student = new Student();
        student.setFirstName("Donyo");
        student.setLastName("Donev");
        student.setEmail("test@donev.com2");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .log()
                .ifValidationFails()
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);
    }
}

