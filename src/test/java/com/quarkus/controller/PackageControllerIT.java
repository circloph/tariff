package com.quarkus.controller;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

@QuarkusIntegrationTest
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PackageControllerIT {

    @Test
    @Order(1)
    void whenAddPackage() {
        String p = "{\"name\":\"internet\",\"category\":\"INTERNET\",\"meaning\":-1, \"deleted\":false}";
        given().contentType(ContentType.JSON).body(p)
                .when().post("/packages")
                .then().statusCode(200)
                .body("name", equalTo("internet"))
                .body("category", equalTo("INTERNET"))
                .body("meaning", equalTo(-1))
                .body("deleted", equalTo(false));
    }

    @Test
    @Order(2)
    void whenUpdatePackage() {
        String update = "{\"name\":\"calls\",\"category\":\"CALLS\",\"meaning\":-1, \"deleted\":true}";
        given().contentType(ContentType.JSON).body(update)
                .when().put("/packages/1")
                .then().statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("calls"))
                .body("category", equalTo("CALLS"))
                .body("meaning", equalTo(-1))
                .body("deleted", equalTo(true));
    }

    @Test
    @Order(3)
    void whenDeletePackageById() {
        given().contentType(ContentType.JSON)
                .when().delete("/packages/1")
                .then().statusCode(204);
    }

}
