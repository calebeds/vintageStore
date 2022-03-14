package org.calebe.quarkus.microservices.book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "number.proxy") //THis annotation is required to connection among both the services to work. Also the config key shortened the config on application.properties
@Path("/api/numbers")
public interface NumberProxy { //This class will established the communication between rest-book and rest-numbers

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    IsbnThirteen generateIsbnNumbers();//Method that will be invoked. It will return IsbnThirteen object which has a String that will retrieve the information from the rest-number api

}