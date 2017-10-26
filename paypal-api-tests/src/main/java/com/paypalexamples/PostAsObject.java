package com.paypalexamples;

import com.jayway.restassured.http.ContentType;
import com.paypalexamples.base.BaseClass;
import com.paypalexamples.models.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class PostAsObject extends BaseClass {

    private static String paymentID;

    @Test
    public void createAPayment() {

        //here we create our
        Redirect_urls red_url = new Redirect_urls();
        red_url.setCancel_url("http://www.hawaii.com");
        red_url.setReturn_url("http://www.amazon.com");

        Details details = new Details();
        details.setHandling_fee("1.00");
        details.setInsurance("0.01");
        details.setShipping("0.03");
        details.setShipping_discount("-1.00");
        details.setSubtotal("30.00");
        details.setTax("0.07");

        //Create Amount
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setDetails(details);
        amount.setTotal("30.11");

        //Set the shipping address
        Shipping_address shipping_address = new Shipping_address();
        shipping_address.setCity("San Jose");
        shipping_address.setCountry_code("US");
        shipping_address.setLine1("4thFloor");
        shipping_address.setLine2("unit34");
        shipping_address.setPhone("011862212345678");
        shipping_address.setPostal_code("95131");
        shipping_address.setRecipient_name("PAB");
        shipping_address.setState("CA");

        //Set Items
        Items item = new Items();
        item.setCurrency("USD");
        item.setDescription("Brown color hat");
        item.setName("hat");
        item.setPrice("3");
        item.setQuantity("5");
        item.setSku("1");
        item.setTax("0.01");

        Items item2 = new Items();
        item2.setCurrency("USD");
        item2.setDescription("Black color hand bag");
        item2.setName("handbag");
        item2.setPrice("15");
        item2.setQuantity("1");
        item2.setSku("product34");
        item2.setTax("0.02");

        List<Items> items = new ArrayList<Items>();
        items.add(item);
        items.add(item2);

        //Item List
        Item_List item_list = new Item_List();
        item_list.setShipping_address(shipping_address);
        item_list.setItems(items);

        //Set payment options
        Payment_Options payment_options = new Payment_Options();
        payment_options.setAllowed_payment_method("INSTANT_FUNDING_SOURCE");

        //Set Transactions
        Transactions transaction = new Transactions();
        transaction.setAmount(amount);
        transaction.setCustom("EBAY_EMS_90048630024435");
        transaction.setDescription("This is the payment transaction description.");
        transaction.setInvoice_number("48787589674");
        transaction.setItem_list(item_list);
        transaction.setPayment_options(payment_options);
        transaction.setSoft_descriptor("ECHI5786786");

        List<Transactions> transactions = new ArrayList<Transactions>();
        transactions.add(transaction);

        //Set payer
        Payer payer = new Payer();
        payer.setPayment_method("paypal");

        //Post object is the whole JSON object that combines all smaller JSON objects we have built
        //and we send it
        PostObj postObj = new PostObj();
        postObj.setIntent("sale");
        postObj.setNote_to_payer("Contact us");
        postObj.setRedirect_urls(red_url);
        postObj.setPayer(payer);
        postObj.setTransactions(transactions);

        //Here we pass the accessToken from the BaseClass in oauth2
        //We pass the object that we have created - postObj to the body
        //And we return the Payment ID to reuse in the second test
        paymentID = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .when()
                .body(postObj)
                .post("/payments/payment")
                .then()
                .log()
                .all()
                .extract()
                .path("id");
    }

    @Test
    public void getPaymentDetails() {
        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .when()
                .get("/payments/payment/" + paymentID)
                .then()
                .log()
                .all();
    }
}
