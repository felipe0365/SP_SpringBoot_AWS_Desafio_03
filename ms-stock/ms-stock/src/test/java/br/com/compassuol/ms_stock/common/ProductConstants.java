package br.com.compassuol.ms_stock.common;

import br.com.compassuol.ms_stock.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductConstants {

    public static final Product PRODUCT = new Product("p1", 5);
    public static final Product INVALID_PRODUCT = new Product("", 5);

    public static final Product P1 = new Product("p1", 5);
    public static final Product P2 = new Product("p2", 5);
    public static final Product P3 = new Product("p3", 5);
    public static final List<Product> PRODUCTS = new ArrayList<Product>() {
        {
            add(P1);
            add(P2);
            add(P3);
        }
    };

}
