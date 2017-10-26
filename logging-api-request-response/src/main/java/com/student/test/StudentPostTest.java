package com.student.test;

import com.student.base.TestBase;
import com.student.model.Student;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class StudentPostTest extends TestBase {

    @Test
    public void createNewStudent(){
        //email address need to be unique
        ArrayList<String> courses = new ArrayList();
        courses.add("Java");
        courses.add("C++");

        Student student = new Student();
        student.setFirstName("Donyo");
        student.setLastName("Donev");
        student.setEmail("donev@donev.com2");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);
    }
}
