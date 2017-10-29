package com.studentapp.junit;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest extends TestBase{



    @Test
    public void getAllStudents(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void thisIsFailedTest(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(500);
    }

    @Pending
    @Test
    public void thisIsPendingTest(){
    }

    @Ignore
    @Test
    public void thisIsSkippedTest(){
    }

    @Test
    public void thisIsTestWithError(){
        System.out.println("This is the error: " + (5/0));
    }

    @Test
    public void fileDoesNotExist() throws FileNotFoundException{
        File file = new File("E://file.text");
        FileReader fileReader = new FileReader(file);
    }

    @Manual
    @Test
    public void thisIsManualTestThatApppearsInReport() throws FileNotFoundException{
        File file = new File("E://file.text");
        FileReader fileReader = new FileReader(file);
    }

    @Title("Getting a list of all students and verifying status code = 200")
    @Test
    public void test001(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}
