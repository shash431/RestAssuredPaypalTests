package com.studentapp.junit;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.Student;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtil;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTests extends TestBase{

    String firstName = "SmokeFirstName" + TestUtil.generateRandomValue();
    String lastName = "SmokeLastName" + TestUtil.generateRandomValue();
    String programme = "ComputeScience" + TestUtil.generateRandomValue();
    String email = TestUtil.generateRandomValue() +  "xyz@Email.com";
    int studentId;

    @Steps
    StudentSerenitySteps steps;

    @Title("This test will create a student")
    @Test
    public void test001(){
        List<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("C#");

        steps.createStudent(firstName, lastName, email, programme, courses)
                .statusCode(201);


        HashMap<String, Object> value = steps.getStudentByFirstName(firstName);

        System.out.println("The value is" + value);

        assertThat(value, hasValue(firstName));

        studentId = (Integer) value.get("id");
    }

    @Title("Update the student added")
    @Test
    public void test002(){

        List<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("C#");

        Student student = new Student(firstName, lastName, email, programme, courses);

        SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .body(student)
                .put("/" + studentId)
                .then()
                .log()
                .all()
                .statusCode(201);
    }
}
