package com.jsoup;

import io.restassured.RestAssured;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

public class JsoupExamplesWithoutLoggingIn {

    private Document document = null;

    @Before
    public void init(){
        //converting the HTML response to a string
        String response = RestAssured.given().when().get("http://localhost:8080/").asString();

        //using Jsoup to parse the string as a document
        document = Jsoup.parse(response);
    }

    @Test
    public void getTitle(){
        //getting the title of the document
        System.err.println("The title of the page is: " + document.title().toUpperCase());
    }

    @Test
    public void extractingElementsByTag(){
        //getting all elements by tag
        Elements elements = document.getElementsByTag("form");

        System.out.println("The number of the form elements is: " + elements.size());

        System.err.println("Printing the Form HTML element");
        for(Element e : elements ){
            System.out.println(e);
        }
    }

    @Test
    public void extractingElementsById(){
        //getting the element by id
        Element element = document.getElementById("command");

        //printing the element's content
        System.out.println("The Element contents are: " + element);

        //printing the text of all tags under the element
        System.out.println("The text in all elements are: " + element.text());

        //getting all links by using a CSS Query
        Elements links = document.select("a[href]");

        //printing all links
        for(Element e : links){
            System.err.println(e);
            //printing just the text
            System.err.println(e.text());
        }
    }
}
