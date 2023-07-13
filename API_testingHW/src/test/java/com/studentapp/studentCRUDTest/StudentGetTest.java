package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class StudentGetTest extends TestBase {
   static ValidatableResponse response;

    @Test
    public void getAllStudentsInfo() {
         response =given()
                .when()
                .get("/list").then();
         response.statusCode(200);



    }

    @Test
    public void getSingleStudentInfo() {
        response =given()
                .pathParam("id",101)
                .when()
                .get("/{id}")
                .then();
        response.statusCode(200);

    }

    @Test
    public void searchStudentWithParameter() {
        response =given()
                .queryParam("programme","Financial Analysis")
                .queryParam("limit",3)
                .when()
                .get("/list")
                .then()
                .log().all();

        response.statusCode(200);
    }

}
