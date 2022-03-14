package org.calebe.quarkus.microservices.book;

import javax.json.bind.annotation.JsonbProperty;

public class IsbnThirteen {
    
    @JsonbProperty("isbn_13")//Mapping to be recognize from the rest-number microservice
    private String isbn13;

    
    public String getIsbn13() {
        return isbn13;
    }


    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

   
}
