package com.reactive.quarkus.municipality.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.reactive.quarkus.municipality.util.ObjectCreation.createBariError;
import static com.reactive.quarkus.municipality.util.ObjectCreation.createFlorence;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
class MunicipalityControllerTest {


    @DisplayName("when call get all municipalities, them i receive the json list")
    @Test
    void get_all_municipalities() {
        given().when()
                .contentType(ContentType.JSON)
                .get("/municipality/getAllMunicipalities")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));

    }

    @DisplayName("when call create municipality, if all is ok, them receive a message with complete operation")
    @Test
    void create_municipality() {
        given().when()
                .contentType(ContentType.JSON)
                .body(createFlorence.get())
                .post("/municipality/createMunicipality")
                .then()
                .statusCode(201)
                .body("message", equalTo("municipality creation is ok"));
    }

    @DisplayName("when call create municipality, if you have a missing field, them receive an error")
    @Test
    void create_municipality_error() {
        given().when()
                .contentType(ContentType.JSON)
                .body(createBariError.get())
                .post("/municipality/createMunicipality")
                .prettyPeek()
                .then()
                .statusCode(400)
                .body("violations[0].message", equalTo("the municipality name is mandatory"));
    }
}
