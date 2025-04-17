


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
* + <<constructor>> Product(int, String, float, int, String)
* + getSellingPrice(): float
* + setSellingPrice(float): void
* + getCustomer(): String
* + setCustomer(String): void
* + getSaleStatus(): boolean
* + setSaleStatus(boolean): void
* + toString(): String
***********************************/


public class Product extends Item {

    private float sellingPrice = 0;
    private String customer = "";
    private boolean onSale = false;

    public Product(String id, String itemName, float unitCost, int quantity, String supplier, String status, String location, float salePrice, String customer, boolean onSale) {
        super(id, itemName, unitCost, quantity, supplier, status, location);

        this.onSale = onSale;
        sellingPrice = salePrice;
        this.customer = customer;
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

    @Override
    public String toString() {
        return super.toString() + "\n   Sale Price: " + Float.toString(sellingPrice) + "\n  Buyer: " + customer +  "\n  On sale: " + Boolean.toString(onSale);
    }



};