package com.studentapp.junit;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;


@UseTestDataFrom("testdata/studentinfo.csv")
@RunWith(SerenityRunner.class)
public class CreateStudentsDataDrivenTest extends TestBase {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course;

    @Steps
    StudentSerenitySteps steps;

    @Title("Data driven test for adding Multiple students to the student app")
    @Test
    public void createMultipleStudents() {
        ArrayList<String> courses = new ArrayList<>();
        courses.add(course);

        StudentSerenitySteps steps = new StudentSerenitySteps();

        steps.createStudent(firstName, lastName, email, programme, courses);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
