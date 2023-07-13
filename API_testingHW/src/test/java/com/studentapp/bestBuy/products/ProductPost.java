package com.studentapp.bestBuy;

import com.studentapp.model.ProductsPojo;
import com.studentapp.testbase.TestBaseBestBuy;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductPost extends TestBaseBestBuy {
    static int idNumber;
    @Test //POST product
    public void postAddNewProduct(){
        ProductsPojo pj = new ProductsPojo();
        pj.setName("Gujrati Battries");
        pj.setType("PowerBank");
        pj.setPrice(199);
        pj.setUpc("0147852369");
        pj.setShipping(10);
        pj.setDescription("Multi pack batteries Including charging cable");
        pj.setManufacturer("Java Groups");
        pj.setModel("GujuRocks");
        pj.setUrl("http://www.bestbuy.com/site/energizer-max-batteries-aa-4-pack/150115.p?id=1051384046217&skuId=150115&cmp=RMXCD");

        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .when()
                .body(pj)
                .post();
        response.then().statusCode(201);
        idNumber = response.then().extract().path("id");
        System.out.println(idNumber);
    }
}
