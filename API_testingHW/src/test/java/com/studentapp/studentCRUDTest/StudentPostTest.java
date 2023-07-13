package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;


public class StudentPostTest extends TestBase {

    @Test
    public void createStudent() {
        ArrayList<String>courses= new ArrayList<>();
        courses.add("selenium");
        courses.add("Restassured");

        StudentPojo studentPojo= new StudentPojo();
        studentPojo.setFirstName("Meet");
        studentPojo.setLastName("pat");
        studentPojo.setEmail("met@gmail.com");
        studentPojo.setProgramme("api testing");
        studentPojo.setCourses(courses);

        given()
                .header("Content-Type", "application/json")
                .body(studentPojo)
                .when()
                .post()
                .then()
                .statusCode(201);

    }
}
