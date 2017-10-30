package com.studentapp.cucumber.serenity;

import com.studentapp.model.Student;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

import static com.studentapp.utils.ReusableSpecifications.getGenericRequestSpec;
import static com.studentapp.utils.ReusableSpecifications.getGenericResponseSpec;

public class StudentSerenitySteps {

    @Step("Creating a student by passing first name: {0}, last name: {1}, email: {2}, programme: {3}, courses: {4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses) {

        Student student = new Student(firstName, lastName, email, programme, courses);

        return SerenityRest.rest().given()
                .spec(getGenericRequestSpec())
                .log()
                .all()
                .when()
                .body(student)
                .post()
                .then()
                .spec(getGenericResponseSpec());
    }

    @Step("Getting a student by passing a first name: {0}")
    public HashMap getStudentByFirstName(String firstName) {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        return SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .spec(getGenericResponseSpec())
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1 + firstName + p2);
    }
}
