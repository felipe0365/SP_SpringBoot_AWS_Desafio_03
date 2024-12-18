package br.com.compassuol.ms_stock.util;

import br.com.compassuol.ms_stock.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class QueryBuilder {
    public static Example<Product> makeQuery(Product product) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
        return Example.of(product, exampleMatcher);
    }
}