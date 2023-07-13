package com.studentapp.bestBuy;

import com.studentapp.model.ProductsPojo;
import com.studentapp.testbase.TestBaseBestBuy;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

public class ProductCRUDTest extends TestBaseBestBuy {
    static ValidatableResponse response;
    static int idNumber;
@Test
    public void getProductList(){
    given()
            .when()
            .log().all()
            .get()
            .then().log().all().statusCode(200);
}
@Test //get
    public void getProductByID(){
response = (ValidatableResponse) given()
        .pathParams("id",43900)
        .when()
        .get("/{id}")
        .then();
response.statusCode(200);
}
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
@Test
    public void getCreatedProductById(){response = (ValidatableResponse) given()
        .pathParams("id",9999681)
        .when()
        .get("/{id}")
        .then();
    response.statusCode(200);
}
@Test //get product by id
public void updateCreatedID(){
    ProductsPojo pj = new ProductsPojo();
    Response response = given()
            .log().all()
            .header("Content-Type","application/json")
            .pathParams("id",9999679)
            .when()
            .body(pj)
            .patch("/{id}");
    response.then().statusCode(200);

}
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
    @Test //Delete
    public void delete(){
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id",9999681 )
                .when()
                .delete("/{id}")
                .then()
                .statusCode(200);
    }

    @Test //Get list of products by multiple  names from categories
    public void checkMultipleProductsNames(){
    ////4. Check the multiple ‘Names’ in the ArrayList
    given()
           // .queryParam("$select[]","name")
            .when()
            .get()
            .then()
            .extract()
            .path("data.categories.name",String.valueOf( hasItems("Alkaline Batteries", "Housewares", "Connected Home & Housewares")));
}
@Test // Get all products, limit to 1 result
    public void getByQueryParameter(){
    given()
            .queryParam("$limit","1")
            .when()
            .get().then()
            .log().all()
            .statusCode(200);
    given()
            .queryParam("id",333179)
            .when()
            .get().then()
            .log().all()
            .statusCode(200);
}
@Test //Get all products, sort by highest price (descending)
    public void getProductByQueryParametersDescendingPrice(){
    given().queryParam("$sort[price]","-1")
            .when()
            .get().then()
            .log().all().statusCode(200);
}
@Test //Get all products, sort by lowest price (ascending)
    public void getProductByQueryParametersAscendingPrice(){
        given().queryParam("$sort[price]","1")
                .when()
                .get().then()
                .log().all().statusCode(200);
    }
    @Test //Get all products, but only show the name and price in the result
    public void getMultipleByQueryParameters(){
        given().queryParam("$select[]","name","price")
                .when()
                .get().then()
                .log().all().statusCode(200);
    }
    @Test //Get products of type HardGood
    public void getProductByType(){
    given()
            .queryParam("type","HardGood")
            .when()
            .get()
            .then()
            .log().all()
            .statusCode(200);
    }
    @Test //Get products less than or equal to $1.00
    public void getProductsFilterByLessPrice(){
    given()
            .queryParam("price[$lte]","1")
            .when()
            .get()
            .then()
            .log().all()
            .statusCode(200);
    }


}


