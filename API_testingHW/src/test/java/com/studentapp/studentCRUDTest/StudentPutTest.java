package com.studentapp.studentinfo;


import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class StudentPutTest extends TestBase {

    @Test
    public void put() {

        ArrayList<String> courses= new ArrayList<>();
        courses.add("selenium1");
        courses.add("Restassured1");

        StudentPojo studentPojo= new StudentPojo();
        studentPojo.setFirstName("Meeter");
        studentPojo.setLastName("patil");
        studentPojo.setEmail("sweta@gmail.com");
        studentPojo.setProgramme("api201 testing");
        studentPojo.setCourses(courses);


        given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", "103")
                .body(studentPojo)
                .when()
                .put("/{id}")
                .then()
                .statusCode(200);
    }

}


