package com.studentapp.bestBuy;

import com.studentapp.model.ProductsPojo;
import com.studentapp.testbase.TestBaseBestBuy;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductPatch extends TestBaseBestBuy {
    @Test // PATCH
    public void patchCrestedProductDetails(){
        ProductsPojo pj = new ProductsPojo();
        pj.setModel("JavaBava");
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParams("id",9999681)
                .when()
                .body(pj)
                .patch("/{id}");
        response.then().statusCode(200);

    }
}
