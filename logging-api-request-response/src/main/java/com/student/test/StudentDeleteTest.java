package com.student.test;

import com.student.base.TestBase;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {

    @Test
    public void updateStudent(){
        given()
                .when()
                .delete("/101")
                .then()
                .statusCode(204);
    }
}
