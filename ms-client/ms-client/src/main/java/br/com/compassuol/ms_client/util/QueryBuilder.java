package br.com.compassuol.ms_client.util;

import br.com.compassuol.ms_client.model.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class QueryBuilder {
    public static Example<Client> makeQuery(Client client) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
        return Example.of(client, exampleMatcher);
    }
}
