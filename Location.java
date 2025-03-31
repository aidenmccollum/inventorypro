

/*********************************** 
* Filename: Location.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A class to represent a location that can store items, providing functionality
*         for managing the location name and its stored items collection.
*
* Attributes:
* - locationName: String
* - storedItems: ArrayList<Item>
*
* Methods:
* + Location(String name, ArrayList<Item> items): Constructor
* + getLocation(): String
* + setLocation(String location): void
* + addItem(Item item): void
* + removeItemByID(int id): void
* + getStoredItems(): ArrayList<Item>
* + setStoredItems(ArrayList<Item> storedItems): void
***********************************/


import java.util.ArrayList;

public class Location {

    private String locationName = "";
    private ArrayList<Item> storedItems = new ArrayList<>();

    public Location(String name, ArrayList<Item> items) {
        locationName = name;
        storedItems = items;
    }

    public String getLocation() {
        return locationName;
    };

    public void setLocation(String location) {
        locationName = location;
    };

    public void addItem(Item item) {
        storedItems.add(item);
    };

    public void removeItemByID(int id) {
        for (int index = 0; index < storedItems.size(); index++) {
            Item item = storedItems.get(index);
            if (item.getID() == id) {
                storedItems.remove(index);
            };
        }
    };

    public ArrayList<Item> getStoredItems() {
        return storedItems;
    }

    public void setStoredItems(ArrayList<Item> storedItems) {
        this.storedItems = storedItems;
    }




};