/*********************************** 
* Filename: Item.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A class to represent inventory items
*
* Attributes:
* - itemID: int
* - locationName: String
* - itemName: String
* - unitCost: float
* - quantity: int
* - supplier: String
* - status: String
* - statusOptions: String[]
*
* Methods:
* + Item(int id, String name, float cost, int numItems, String provider): Constructor
* + getID(): int
* + setID(int): void
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
***********************************/




public class Item {
    private int itemID = 0;
    private String locationName = "";
    private String itemName = "";
    private float unitCost = 0;
    private int quantity = 0;
    private String supplier = "";
    private String status = "";

    private final static String[] statusOptions = {"Requested"};

    public Item(int id, String name, float cost, int numItems, String provider) {
        itemID = id;
        itemName = name;
        unitCost = cost;
        quantity = numItems;
        supplier = provider;
        status = "Requested";
    }

    public int getID() {
        return itemID;
    }

    public void setID(int id) {
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
};