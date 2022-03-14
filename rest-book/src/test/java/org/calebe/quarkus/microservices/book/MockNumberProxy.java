package org.calebe.quarkus.microservices.book;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.test.Mock;

@Mock
@RestClient
public class MockNumberProxy implements NumberProxy{

    @Override
    public IsbnThirteen generateIsbnNumbers() {
        IsbnThirteen isbnThirteen = new IsbnThirteen();
        isbnThirteen.setIsbn13("13-Mock");
        return isbnThirteen;
    }
    
}
