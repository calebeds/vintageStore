package org.calebe.quarkus.microservices.number;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")//Root path
@OpenAPIDefinition( //All this information wiil be shown at swagger/check localhost:8701/q/swagger-ui
    info = @Info( //Info
        title = "Number Microservice",
        description = "This microservice generates book numbers",
        version = "1.0",
        contact = @Contact(
            name = "@calebeds",
            url = "https://instagram.com/calebeds"
        )
    ),
    externalDocs = @ExternalDocumentation(
        url = "https://github.com/calebeds",
        description = "Some repositories"
    ),
    tags = {
        @Tag(
            name = "api",
            description = "Public API can be used by anybody"
        ),
        @Tag(
            name = "Numbers",
            description = "Anybody interested in numbers"
        )
    }
  
)
public class NumberMicroservice extends Application {
    
}
