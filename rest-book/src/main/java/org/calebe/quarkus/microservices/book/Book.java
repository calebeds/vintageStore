package org.calebe.quarkus.microservices.book;

import java.time.Instant;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "This is a book")//Schema section on swagger-ui
public class Book {
    //Attributes
    @JsonbProperty("isbn_13") //Serializes into this new name when a Json representation is got
    @Schema(required = true)//It will show a asterisk on swagger-ui
    private String isbn13;
    @Schema(required = true)
    private String title;
    private String author;
    @JsonbProperty("year_of_publication")
    private int yearOfPublication;
    private String genre;
    @JsonbDateFormat("yyyy/MM/dd")
    @JsonbProperty("creation_date")
    @Schema(implementation = String.class, format = "date")
    private Instant creationDate;

    //Setters and getters
    public String getIsbn13() {
        return isbn13;
    }
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYearOfPublication() {
        return yearOfPublication;
    }
    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Instant getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", creationDate=" + creationDate + ", genre=" + genre + ", isbn13=" + isbn13
                + ", title=" + title + ", yearOfPublication=" + yearOfPublication + "]";
    }

}
