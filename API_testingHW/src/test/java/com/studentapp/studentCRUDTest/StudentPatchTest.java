package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class StudentPatchTest extends TestBase {

    @Test
    public void updateStudentWithPatch(){

        StudentPojo studentPojo= new StudentPojo();
        studentPojo.setEmail("shweta@gmail.com");
        studentPojo.setProgramme("manual testing");


        given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id","103")
                .body(studentPojo)
                .when()
                .patch("/{id}")
                .then()
                .statusCode(200);
    }
}
