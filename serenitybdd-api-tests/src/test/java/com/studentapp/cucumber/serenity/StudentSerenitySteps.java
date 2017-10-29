package com.studentapp.cucumber.serenity;

import com.studentapp.model.Student;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;

public class StudentSerenitySteps {

    @Step("Creating a student")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses){

        Student student = new Student(firstName, lastName, email, programme, courses);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .post()
                .then();
    }
}
