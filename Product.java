


/***********************************
* Filename: Product.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A subclass of Item that represents a product available for sale,
*         with additional attributes for pricing, customer information, and sale status.
*
* Attributes:
* - sellingPrice: float
* - customer: String
* - onSale: boolean
*
* Methods:
* +getSellingPrice(): float
* +setSellingPrice(float sellingPrice): void
* +getCustomer(): String
* +setCustomer(String customer): void
* +getSaleStatus(): boolean
* +setSaleStatus(boolean onSale): void
*
* Constructor:
* +Product(int id, String name, float cost, int numItems, String provider)
***********************************/


public class Product extends Item {

    private float sellingPrice = 0;
    private String customer = "";
    private boolean onSale = false;

    public Product(int id, String name, float cost, int numItems, String provider) {
        super(id, name, cost, numItems, provider);
        
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public boolean getSaleStatus() {
        return onSale;
    }

    public void setSaleStatus(boolean onSale) {
        this.onSale = onSale;
    }



};