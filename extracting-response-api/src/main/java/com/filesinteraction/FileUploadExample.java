package com.filesinteraction;

import org.junit.Test;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;

public class FileUploadExample {

    //Upload a .gif file to zamzar.com and convert it into a .png file
    @Test
    public void uploadFileExample() {
        String apiKey = "8e3b0aa29db5be79893b67e25e4ed29eede85d89";
        String endpoint = "https://sandbox.zamzar.com/v1/jobs";
        File inputFile = new File(System.getProperty("user.dir") + File.separator + "dancing_banana.gif");

        System.out.println("Size of the file is: " + inputFile.length());
        System.out.println("The path to the file I upload is: " + inputFile.getAbsolutePath());


        given()
                .auth()
                .basic(apiKey, "")
                .multiPart("source_file", inputFile)
                .multiPart("target_format", "png")
                .when()
                .post(endpoint)
                .then()
                .log()
                .all();
    }
}
