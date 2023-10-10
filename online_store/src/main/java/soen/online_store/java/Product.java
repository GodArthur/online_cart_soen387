
package soen.online_store.java;

/**
 *
 * @author Kojo
 */
public class Product {
    
   private String name;
   private String vendor;
   private String URLSlug;
   private String SKU;
   private double price;
   
   public Product(String name, String vendor, String URLSlug, String SKU, double price){
       
       this.name = name;
       this.SKU = SKU;
       this.vendor = vendor;
       this.URLSlug = URLSlug;
       this.price = price;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
