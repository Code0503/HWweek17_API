package com.studentapp.studentinfo;


import com.studentapp.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {

    @Test
    public void delete(){
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "103")
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);
    }


}
