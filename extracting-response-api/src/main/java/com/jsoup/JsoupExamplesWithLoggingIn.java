package com.jsoup;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.session.SessionFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

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

        //getting all email IDs by using CSS selector
        Elements emailIds = document.select("table tbody tr td:nth-child(4)");

        System.err.println("The size of the table is: " + emailIds.size());

        ArrayList<String> actualValue = new ArrayList<String>();
        //printing all email ids
        for(Element e : emailIds){
            System.out.println(e.text());
            actualValue.add(e.text());
        }

        assertThat(actualValue, hasItem("molestie@vitaesemper.ca"));
    }
}
