package org.calebe.quarkus.microservices.number;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@QuarkusTest
public class NumberResourceTest {

    @Test
    public void shouldGetNumberResource() {
        given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)//Header
          .when().get("/api/numbers")
          .then()
             .statusCode(200)
             .body("isbn_13", startsWith("13-")) //Test if the value of isbn_13 contains 13 in the beggining
             .body("isbn_10", startsWith("10-")) //Test if the value of isbn_10 contains 10 in the beggining
             .body(not(hasKey("generationDate")));//Test if there isn't a key named generationDate
    }

}