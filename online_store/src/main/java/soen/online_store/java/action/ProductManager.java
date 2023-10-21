package soen.online_store.java.action;

import java.util.*;
import soen.online_store.java.*;

/**
 * Class manages and handles Product operations
 *
 * @author Korjon Chang
 */
public class ProductManager {

    List<Product> products;

    public ProductManager(List<Product> products) {
        this.products = products;
    }

    public void CreateProduct(String sku, String name) {

        //Creating and adding the new product to the
        //list of products
        products.add(new Product(sku, name));
    }

    public void updateProduct(String SKU, String name, String description, String vendor, String URLSlug, double price) {

        //Finding the specific product in the list
        //of products by the SKU
        Product p = products.stream()
                .filter(e -> e.getSKU().equals(SKU))
                .findFirst()
                .orElse(null);

        //Checking if the fields passed were null
        if (name != null) {
            p.setName(name);
        }
        if (description != null) {
            p.setDescription(description);
        }
        if (vendor != null) {

            p.setVendor(vendor);

        }
        if (URLSlug != null) {

            p.setURLSlug(URLSlug);

        }
        if (price != -1) {

            p.setPrice(price);

        }
    }

    public Product getProduct(String SKU) {

        Product p = products.stream()
                .filter(e -> e.getSKU().equals(SKU))
                .findFirst()
                .orElse(null);

        return p;
    }

    public Product getProductBySlug(String slug) {

        Product p = products.stream()
                .filter(e -> e.getURLSlug().equals(slug))
                .findFirst()
                .orElse(null);

        return p;
    }

    public List<Product> getAllProducts() {

        return products;
    }
}
