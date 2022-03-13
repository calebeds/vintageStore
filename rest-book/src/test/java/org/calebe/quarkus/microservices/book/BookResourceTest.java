package org.calebe.quarkus.microservices.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void shouldCreateABook() {
        given()
            .formParam("title", "Understanding Quarkus") //Setting the form params that wiil be passed on the request
            .formParam("author", "OLIVEIRA, Calebe")
            .formParam("year", 2020)
            .formParam("genre", "IT")
        .when()
            .post("/api/books") //Route where tests will happen
        .then()
            .statusCode(201) //Status code
            .body("isbn_13", startsWith("13-")) //Testing all json properties
            .body("title", is("Understanding Quarkus"))
            .body("author", is("OLIVEIRA, Calebe"))
            .body("year_of_publication", is(2020))
            .body("genre", is("IT"))
            .body("creation_date",startsWith("20"));


    }

}