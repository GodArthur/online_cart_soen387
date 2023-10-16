package soen.online_store.java.action;

import soen.online_store.java.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents a product catalog that can be exported.
 * @author Korjon Chang
 */
public class ProductCatalog {
    
    private final ProductManager productManager;

    public ProductCatalog(ProductManager productManager) {
        this.productManager = productManager;
    }

    /**
     * Exports the product catalog into a CSV file.
     * @return File representing the CSV file.
     * @throws IOException if there's an issue with file writing.
     */
    public File downloadProductCatalog() throws IOException {
        File file = new File("product_catalog.csv");

        try (FileWriter writer = new FileWriter(file)) {
            // Write the header
            writer.write("Name,Description,Vendor,URL Slug,SKU,Price\n");

            // Retrieve products from the product manager
            List<Product> products = productManager.getAllProducts();

        if (products.isEmpty()) {
            //Throws message if there are no products
            throw new IllegalStateException("No products for retrieval");
        }

            // Write product details to the CSV
            for (Product product : products) {
                writer.write(String.format(
                    "\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%.2f\"\n",
                    product.getName(),
                    product.getDescription(), 
                    product.getVendor(),       
                    product.getURLSlug(),
                    product.getSKU(),
                    product.getPrice()
                ));
            }
        }

        return file;
    }
}
