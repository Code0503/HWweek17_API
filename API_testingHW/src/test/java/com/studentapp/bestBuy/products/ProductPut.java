package com.studentapp.bestBuy;

import com.studentapp.model.ProductsPojo;
import com.studentapp.testbase.TestBaseBestBuy;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductPut extends TestBaseBestBuy {
    @Test // PUT
    public void putAndSetChangesInCrestedProductDetails(){
        ProductsPojo pj = new ProductsPojo();
        pj.setName("Guju Battries");
        pj.setType("Powered Batteries");
        pj.setPrice(199);
        pj.setUpc("0147852369");
        pj.setShipping(10);
        pj.setDescription("Multi pack batteries Including charging cable");
        pj.setManufacturer("JavaMakesBava");
        pj.setModel("GujuRocks");
        pj.setUrl("http://www.bestbuy.com/site/energizer-max-batteries-aa-4-pack/150115.p?id=1051384046217&skuId=150115&cmp=RMXCD");


        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id",9999682)
                .when()
                .body(pj)
                .put("/{id}");
        response.then().statusCode(200);

    }

}
