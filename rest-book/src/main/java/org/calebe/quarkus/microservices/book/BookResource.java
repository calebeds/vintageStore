package org.calebe.quarkus.microservices.book;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/api/books")
@Tag(name = "Book REST endpoint") //Check swagger-ui
public class BookResource {

    //@Inject Opcional
    @RestClient//Annotation to the proxy
    NumberProxy proxy;

    @Inject
    Logger logger;

    @POST
    @Produces(MediaType.APPLICATION_JSON)//It will return a Json Format
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)//It will get the parameter from urlencoded form, in our case, in the curl. ex: curl -X POST http://localhost:8702/api/books -d "title=Quarkus&author=Calebe&year=2021&genre=IT"
    @Operation(
        summary = "Creates a Book",
        description = "Creates a Book with an ISBN number"
    )
    @Fallback(
        fallbackMethod = "fallingBackOnCreatingABook" //It will invoke the method below in case of an error creating the book, like fail in communication
    )
    @Retry(
        maxRetries = 1,//How many it will tries
        delay = 2000//Delay for a response
    )
    public Response createABook(//The form params when passing it in the curl; annotation @FormParam required
        @FormParam("title") String title, 
        @FormParam("author") String author, 
        @FormParam("year") int yearOfPublication, 
        @FormParam("genre") String genre
        ) {
        Book book = new Book();//New Book instance

        //book.setIsbn13("13-We will get it later from the Number microservice");
        book.setIsbn13(proxy.generateIsbnNumbers().getIsbn13()); //Getting from the rest-number microservice
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);
        book.setCreationDate(Instant.now());

        logger.info("Book created: " + book);

        return Response.status(201).entity(book).build();//It returns a Response 201/created and a Json
    }

    public Response fallingBackOnCreatingABook(
            String title, String author, int yearOfPublication, String genre 
        ) throws FileNotFoundException {
        Book book = new Book();
        book.setIsbn13("Will bet set later");
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);
        book.setCreationDate(Instant.now());
        saveBookOnDisk(book);
        logger.warn("Book saved on Disk: " + book);
        return Response.status(206).entity(book).build();
    }

    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        String bookJson = JsonbBuilder.create().toJson(book);//Parse the book to Json archive.It is in the root of the project. Don't know to change it
        try(PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) { //The printwriter will create an archive .json
            out.println(bookJson);//Prints to the archive the json representation of the book
        } 
    }
}