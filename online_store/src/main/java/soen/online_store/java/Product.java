
package soen.online_store.java;

/**
 * Entity Class for products
 * 
 * @author Korjon Chang
 */
public class Product {
    
   private String name;
   private String description;
   private String vendor;
   private String URLSlug;
   private String SKU;
   private double price;
    private int quantity; // This is the new attribute for quantity
   
   public Product(String name, String description, String vendor, String URLSlug, String SKU, double price){
       
       this.name = name;
       this.description = description;
       this.SKU = SKU;
       this.vendor = vendor;
       this.URLSlug = URLSlug;
       this.price = price;
   }
   
   public Product(String SKU, String name){
       
       this.name = name;
       this.SKU = SKU;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getURLSlug() {
        return URLSlug;
    }

    public void setURLSlug(String URLSlug) {
        this.URLSlug = URLSlug;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
   
   
}
