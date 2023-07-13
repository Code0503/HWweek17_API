package com.studentapp.studentCRUDTest;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StudentTest extends TestBase {
    @Test
    public void getListOfStudentInfo(){
        given()
                .when()
                .get("/list").then().statusCode(200);
    }
    @Test
    public void getStudentID(){
        ValidatableResponse response= given()
                .when().get("/4") // putting student id in get by slas and id number will attach in baseUrl and then in base path
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void postCreatNewStudenData(){ // created student pojo class in main class using encapsulation OPPS concept
        ArrayList<String>courses = new ArrayList<>();
        courses.add("WebDriverTools");
        courses.add("Restassured");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Guju");
        studentPojo.setLastName("Rocks");
        studentPojo.setEmail("guju@Rocks.co.in");
        studentPojo.setProgramme("API Automation");
        studentPojo.setCourses(courses);

        ValidatableResponse response = given()
                .header("Content-Type","application/json")
                .body(studentPojo)
                .log().all()
                .when().post().then().statusCode(201);
    }
    @Test
    public void getCreatedStudentID(){
        ValidatableResponse response = given()
                .when().get("/101")
                .then().log().body().statusCode(200);

    }
    @Test
    public void patchChangeStudentDetail(){
        //change data by calling class object
        StudentPojo studentPojo = new StudentPojo();
   studentPojo.setEmail("guju_rocks@gmail.com");
   // passing all changes written above in below student id
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id","101")
                .body(studentPojo)
                .when()
                .patch("/{id}")
                .then()
                .statusCode(200);
    }
    @Test
    public void putChangeStudentDetails(){
        //change data by calling class object
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Gujrati");
        studentPojo.setLastName("Rocks");
        studentPojo.setEmail("guju_rocks@gmail.com");
        // passing all changes written above in below student id
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id","101")
                .body(studentPojo)
                .when()
                .patch("/{id}")
                .then()
                .statusCode(200);
    }
    @Test
    public void deleteCreatedStudentData(){
     given()
             .log().all()
             .header("Content-Type", "application/json")
             .pathParam("id","101")
             .when()
             .delete("{id}")
             .then()
             .statusCode(204);
    }
    @Test 
    public void dataRetriveByID(){
        HashMap<String, Object> studentMap = given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .path("findAll{it.firstName == 'Guju'}.get(0)");
        int studentID = (int) studentMap.get("id");
        System.out.println(studentID);
    }
    @Test
    public void getByQueryParameter(){
        given()
                .queryParam("programme","API Automation")
                .when()
                .get("/list")
                .then().log().all().statusCode(200);
    }

}
