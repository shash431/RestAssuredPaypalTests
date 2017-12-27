package com.jsoup;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.session.SessionFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;

public class JsoupExamplesWithLoggingIn {

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
    public void extractingEmailInformation(){
        String response = RestAssured.given().sessionId(sessionFilter.getSessionId()).when().
                get("/student/list").asString();
        Document document = Jsoup.parse(response);

        System.out.println(document);
    }


}
