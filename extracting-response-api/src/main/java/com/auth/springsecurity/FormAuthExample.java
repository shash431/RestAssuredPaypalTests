package com.auth.springsecurity;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.session.SessionFilter;
import org.junit.BeforeClass;
import org.junit.Test;

public class FormAuthExample {

    private static SessionFilter sessionFilter;

    @BeforeClass
    public static void init(){
        sessionFilter = new SessionFilter();

        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.given().auth().form("user", "user", new FormAuthConfig("/login","uname","pwd"))
                .filter(sessionFilter)
                .get();

        System.err.println(sessionFilter.getSessionId());
    }

    @Test
    public void getAllStudents(){
        RestAssured.given()
                .sessionId(sessionFilter.getSessionId())
                .get("/student/list")
                .then()
                .log().all();
    }
}
