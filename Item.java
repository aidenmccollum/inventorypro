/*********************************** 
* Filename: Item.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A class to represent inventory items
*
* Attributes:
* - itemID: String
* - locationName: String
* - itemName: String
* - unitCost: float
* - quantity: int
* - supplier: String
* - status: String
*
* Methods:
* + <<constructor>> Item(String, String, float, int, String, String, String)
* + getID(): String
* + setID(String): void
* + getLocationName(): String
* + setLocationName(String): void
* + getItemName(): String
* + setItemName(String): void
* + getUnitCost(): float
* + setUnitCost(float): void
* + getQuantity(): int
* + setQuantity(int): void
* + getSupplier(): String
* + setSupplier(String): void
* + getStatus(): String
* + setStatus(String): void
* + toString(): String
***********************************/




abstract public class Item {
    private String itemID = "";
    private String locationName = "";
    private String itemName = "";
    private float unitCost = 0;
    private int quantity = 0;
    private String supplier = "";
    private String status = "";

    public Item(String id, String name, float cost, int numItems, String provider, String status, String location) {
        itemID = id;
        itemName = name;
        unitCost = cost;
        quantity = numItems;
        supplier = provider;
        this.status = status;
        locationName = location;
    }

    public String getID() {
        return itemID;
    }

    public void setID(String id) {
        itemID = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String name) {
        locationName = name;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        itemName = name;
    }

    public float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(float cost) {
        unitCost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int numItems) {
        quantity = numItems;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String provider) {
        supplier = provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "  Item ID: " + itemID +
               "\n  Name: " + itemName +
               "\n  Location: " + locationName +
               "\n  Unit Cost: $" + String.format("%.2f", unitCost) +
               "\n  Quantity: " + quantity +
               "\n  Supplier: " + supplier +
               "\n  Status: " + status;
    }


};