package com.filesinteraction;

import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileDownloadExample {

    //Download a file and compare it with an expected file
    //check the size of the file
    @Test
    public void verifyDownloadedFile() {
        //Read the input file through a file object
        //we use File.separator so that all will be unique for Windows, Linux
        //System.getProperty will take us to the base directory of the project
        File inputFile = new File(System.getProperty("user.dir") + File.separator + "geckodriver-v0.19.0-arm7hf.tar.gz");

        //Length of input file
        int expectedSize = (int) inputFile.length();

        //2257631
        System.out.println("The size of the expected fies is: " + expectedSize);

        //Read the downloaded File
        byte[] actualValue =
                given()
                .when()
                .get("https://github.com/mozilla/geckodriver/releases/download/v0.19.0/geckodriver-v0.19.0-arm7hf.tar.gz")
                .then()
                //from the response we can extract the file as Byte Array
                .extract()
                .asByteArray();

        System.out.println("The size of the actual file is: " + actualValue.length + " bytes");

        assertThat(expectedSize, equalTo(actualValue.length));
    }
}
