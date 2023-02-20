package com.quarkus.controller;

import com.quarkus.repository.CleanDB;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

@QuarkusIntegrationTest
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TariffControllerIT {

    @Test
    @Order(1)
    void whenAddTariff() {
        String tariff = "{\"name\":\"Kind tariff\",\"archived\":\"false\",\"deleted\":\"false\"}";
        given().contentType(ContentType.JSON).body(tariff)
                .when().post("/tariffs")
                .then().statusCode(200)
                .body("name", equalTo("Kind tariff"))
                .body("archived", equalTo(false))
                .body("deleted", equalTo(false));
    }

    @Test
    @Order(2)
    void whenUpdateTariff() {
        String tariff = "{\"name\":\"Big tariff\",\"archived\":false,\"deleted\":false}";
        given().contentType(ContentType.JSON)
                .body(tariff)
                .when().put("/tariffs/{id}", 1)
                .then().statusCode(200)
                .body("name", equalTo("Big tariff"))
                .body("archived", equalTo(false))
                .body("deleted", equalTo(false));
    }

    @Test
    @Order(5)
    void whenDeleteTariffById() {
        given().contentType(ContentType.JSON)
                .when().delete("/tariffs/1")
                .then().statusCode(204);
    }

    @Test
    @Order(3)
    void whenAddPackageToTariff() {
        String p = "{\"name\":\"internet\",\"category\":\"INTERNET\",\"meaning\":-1, \"deleted\":false}";
        given().contentType(ContentType.JSON).body(p)
                .when().post("/tariffs/1")
                .then().statusCode(200)
                .body("name", equalTo("Big tariff"))
                .body("archived", equalTo(false))
                .body("deleted", equalTo(false))
                .body("packages.size()", is(1));
    }

    @Test
    @Order(4)
    void whenGetTariffs() {
        given().contentType(ContentType.JSON)
                .when().get("/tariffs")
                .then().statusCode(200)
                .body("size()", is(1));
    }
}
