package com.student.base;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {
    @BeforeClass
    public static void init() {
        //http://localhost:8085/student
        //here we set up our base URI
        RestAssured.baseURI = "http://localhost";

        //this is the port
        RestAssured.port = 8085;

        //this is our base path
        RestAssured.basePath = "/student";
    }
}
