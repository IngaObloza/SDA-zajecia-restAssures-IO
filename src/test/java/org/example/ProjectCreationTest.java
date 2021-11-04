package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.thucydides.junit.listeners.TestCounter;
import org.apache.commons.io.input.TeeInputStream;
import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;


public class ProjectCreationTest {

    @Test
    public void userCanCreateAProjectTest(){

        RestAssured.baseURI = "https://api.todoist.com";
        RestAssured.basePath = "/rest/v1/";
        RestAssured.requestSpecification = RestAssured.given().header("Authorization","Bearer ccddbf38cc54490ede86741949d9400575f050c1")
                .contentType(ContentType.JSON);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String projectName = "DrugiTest";

        RestAssured.given()
                .body(format("{\"name\": \"%s\"}", projectName))
                .when()
                .post("/projects")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo(projectName))
                .header("Content-Type",equalTo("application/json"));


    }
}
