package com.studentapp.bestBuy;

import com.studentapp.testbase.TestBaseBestBuy;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductGet extends TestBaseBestBuy {
    static ValidatableResponse response;
    @Test //get
    public void getProductByID(){
        response = (ValidatableResponse) given()
                .pathParams("id",43900)
                .when()
                .get("/{id}")
                .then();
        response.statusCode(200);
    }
}
