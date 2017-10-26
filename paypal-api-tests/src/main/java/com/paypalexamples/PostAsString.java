package com.paypalexamples;

import com.paypalexamples.base.BaseClass;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class PostAsString extends BaseClass {

    @Test
    public void createPayment() {
        String body = "{\n" +
                "  \"intent\": \"sale\",\n" +
                "  \"payer\": {\n" +
                "  \"payment_method\": \"paypal\"\n" +
                "  },\n" +
                "  \"transactions\": [\n" +
                "  {\n" +
                "    \"amount\": {\n" +
                "    \"total\": \"30.11\",\n" +
                "    \"currency\": \"USD\",\n" +
                "    \"details\": {\n" +
                "      \"subtotal\": \"30.00\",\n" +
                "      \"tax\": \"0.07\",\n" +
                "      \"shipping\": \"0.03\",\n" +
                "      \"handling_fee\": \"1.00\",\n" +
                "      \"shipping_discount\": \"-1.00\",\n" +
                "      \"insurance\": \"0.01\"\n" +
                "    }\n" +
                "    },\n" +
                "    \"description\": \"The payment transaction description.\",\n" +
                "    \"custom\": \"EBAY_EMS_90048630024435\",\n" +
                "    \"invoice_number\": \"48787589673\",\n" +
                "    \"payment_options\": {\n" +
                "    \"allowed_payment_method\": \"INSTANT_FUNDING_SOURCE\"\n" +
                "    },\n" +
                "    \"soft_descriptor\": \"ECHI5786786\",\n" +
                "    \"item_list\": {\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "      \"name\": \"hat\",\n" +
                "      \"description\": \"Brown hat.\",\n" +
                "      \"quantity\": \"5\",\n" +
                "      \"price\": \"3\",\n" +
                "      \"tax\": \"0.01\",\n" +
                "      \"sku\": \"1\",\n" +
                "      \"currency\": \"USD\"\n" +
                "      },\n" +
                "      {\n" +
                "      \"name\": \"handbag\",\n" +
                "      \"description\": \"Black handbag.\",\n" +
                "      \"quantity\": \"1\",\n" +
                "      \"price\": \"15\",\n" +
                "      \"tax\": \"0.02\",\n" +
                "      \"sku\": \"product34\",\n" +
                "      \"currency\": \"USD\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"shipping_address\": {\n" +
                "      \"recipient_name\": \"Brian Robinson\",\n" +
                "      \"line1\": \"4th Floor\",\n" +
                "      \"line2\": \"Unit #34\",\n" +
                "      \"city\": \"San Jose\",\n" +
                "      \"country_code\": \"US\",\n" +
                "      \"postal_code\": \"95131\",\n" +
                "      \"phone\": \"011862212345678\",\n" +
                "      \"state\": \"CA\"\n" +
                "    }\n" +
                "    }\n" +
                "  }\n" +
                "  ],\n" +
                "  \"note_to_payer\": \"Contact us for any questions on your order.\",\n" +
                "  \"redirect_urls\": {\n" +
                "  \"return_url\": \"https://www.paypal.com/return\",\n" +
                "  \"cancel_url\": \"https://www.paypal.com/cancel\"\n" +
                "  }\n" +
                "}'";

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .when()
                .body(body)
                .post("payments/payment")
                .then()
                .log()
                .all();
    }
}
