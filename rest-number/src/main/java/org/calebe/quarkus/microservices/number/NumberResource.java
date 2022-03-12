package org.calebe.quarkus.microservices.number;

import java.time.Instant;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;




@Path("/api/numbers")
@Tag(name = "Number REST Endpoint")//Tag form microprofile openapi, it will be reflected on swagger
public class NumberResource {

    @Inject
    Logger logger;//For logging on the console

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Generates book numbers",
        description = "ISBN 13 adn ISBN 10"
    )// Other microprofile annotation
    public IsbnNumbers generateIsbnNumbers() { //Generates Isbn numbers, that is, from the books
        IsbnNumbers isbnNumbers = new IsbnNumbers();    //Instantiate the object
        isbnNumbers.setIsbn13("13-" + new Random().nextInt(1000_000_000)); //Set an random isbn13 number
        isbnNumbers.setIsbn10("10-" + new Random().nextInt(100_000));//Set an random isbn10 number
        isbnNumbers.setGenerationDate(Instant.now()); //Generates a date from the class Instant
        logger.info("Numbers has been generated " + isbnNumbers); //It will show on the console this message when a get request is sent
        return isbnNumbers;
    }

    
}