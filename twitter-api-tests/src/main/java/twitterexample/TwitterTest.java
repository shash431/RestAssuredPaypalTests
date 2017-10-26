package twitterexample;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TwitterTest {

    private String consumerKey = "";
    private String consumerSecret = "";
    private String accessTokenSecret = "";
    private String secretToken = "";

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";
    }

    @Test
    public void createTweet(){
        given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessTokenSecret, secretToken)
                .queryParam("status", "Bulgaria was on second place in wine export in the world in the 80's")
                .when()
                .post("/update.json")
                .then()
                .log()
                .all();
    }


    @Test
    public void getTweet(){
        given()
                .auth()
                .oauth(consumerKey, consumerSecret, accessTokenSecret, secretToken)
                .queryParam("screen_name", "Bulgaria_United")
                .when()
                .get("/user_timeline.json")
                .then()
                .log()
                .all();
    }
}
