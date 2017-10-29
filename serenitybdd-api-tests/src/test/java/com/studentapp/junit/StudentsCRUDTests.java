package com.studentapp.junit;

import com.studentapp.model.Student;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtil;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(SerenityRunner.class)
public class StudentsCRUDTests extends TestBase{

    String fisrtName = "SmokeFirstName" + TestUtil.generateRandomValue();
    String lastName = "SmokeLastName" + TestUtil.generateRandomValue();
    String programme = "ComputeScience" + TestUtil.generateRandomValue();
    String email = TestUtil.generateRandomValue() +  "xyz@Email.com";

    @Title("This test will create a student")
    @Test
    public void createStudent(){
        List<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("C#");

        Student student = new Student(fisrtName, lastName, email, programme, courses);

        SerenityRest.given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .post()
                .then()
                .log()
                .all()
                .statusCode(201);
    }
}
