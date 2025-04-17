
/*********************************** 
* Filename: ItemDatabase.java
* Author: Aiden McCollum
* Created: Mark 28th, 2025
* Purpose: A class to manage a database of items, providing functionality
*         for adding, removing, finding, and listing items in the collection.
*
* Attributes:
* - items: ArrayList<Item>
* - statusOptions: String[]
*
* Methods:
* + <<constructor>> ItemDatabase()
* + addItem(Item item): void
* + listItems(): ArrayList<String>
* + findItemByID(String): Item
* + getUID(String): String
* + validateStatus(String): String
* + fetchItemFileData(): void
* + setItemFileData(): boolean
***********************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemDatabase {

    private ArrayList<Item> items = new ArrayList<>();
    private final static String[] statusOptions = {"Purchased", "In Transit", "Arrived", "In Stock", "Out of Stock"};

    public ItemDatabase() {
        fetchItemFileData();
    };

    public void addItem(Item item) {
        items.add(item);
    };

    public ArrayList<String> listItems() {
        ArrayList<String> itemList = new ArrayList<>();
        for (Item item : items) {
            // itemList.add(item.toString());
            itemList.add(item.getID());
        }
        return itemList;
    };

    public Item findItemByID(String ID) {
        for (Item item: items) {
            if (item.getID().equals(ID)) {
                return item;
            }
        }

        return null;
    };

    public String getUID(String itemType){
        boolean uidUnique = false;

        String uid = "";

        while (uidUnique == false) {
            uid = itemType + (int)(Math.random() * 100000);
            
            if (findItemByID(uid) == null){
                uidUnique = true;
            }
        }

        return  uid;
    }

    public String validateStatus(String statusStr) {
        for (String status:statusOptions) {
            if (status.equalsIgnoreCase(statusStr)) {
                return status;
            }
        }

        return null;
    }

    public void fetchItemFileData() {
        try {
            Scanner infile = new Scanner(new File("itemsData.csv"));
            infile.nextLine(); //eat up the header line
            while(infile.hasNext()) {
                String line = infile.nextLine();
                String[] pieces = line.split(",");

                if (pieces.length != 9 && pieces.length != 10) {
                    continue; //skip the line
                }try{
                    parseItemData(pieces);
                }
                catch(NumberFormatException e) {
                    continue; //skip the line
                }
            }
            infile.close();
        } catch(FileNotFoundException e) {
            System.err.println("Couldn't find the file: " + e);
        }
    }

    public void parseItemData(String[] pieces) {
        String id = pieces[0];
        String name = pieces[1];
        float unitCost = Float.parseFloat(pieces[2]);
        int quantity = Integer.parseInt(pieces[3]);
        String supplier = pieces[4];
        String status = pieces[5];
        String location = pieces[6];
        
        if (pieces.length == 9) { // its an expense
            String expenseType = pieces[7];
            int lifetime = Integer.parseInt(pieces[8]);

            Expense newExpense = new Expense(id, name, unitCost, quantity, supplier, status, location, expenseType, lifetime);
            items.add(newExpense);               
        } else if (pieces.length == 10) { // its a product
            float salePrice = Float.parseFloat(pieces[7]);
            String buyer = pieces[8];
            boolean onSale = Boolean.parseBoolean(pieces[9]);

            Product newProduct = new Product(id, name, unitCost, quantity, supplier, status, location, salePrice, buyer, onSale);
            items.add(newProduct);
        } 
    }

    public boolean setItemFileData() {
        try {
            PrintWriter outfile = new PrintWriter(new File("itemsData.csv"));
            outfile.append("ID,name,unitCost,quantity,supplier,status,location,salePrice/expenseType, buyer/lifetime,onsale");
            for (Item item: items) {

                if (item instanceof Product) {
                    outfile.append("\n" + item.getID() + "," + item.getItemName() + "," + item.getUnitCost() + "," + 
                    item.getQuantity() + "," + item.getSupplier() + "," + item.getStatus() + "," + item.getLocationName() + "," + ((Product) item).getSellingPrice() + 
                    "," + ((Product) item).getCustomer() + "," + ((Product) item).getSaleStatus());
                } else {
                    outfile.append("\n" + item.getID() + "," + item.getItemName() + "," + item.getUnitCost() + "," + item.getQuantity() + 
                    "," + item.getSupplier() + "," + item.getStatus() + "," + item.getLocationName() + "," + ((Expense) item).getExpenseType() + "," + ((Expense) item).getTimespan());
                }
                
            }
            outfile.close();
        } catch(FileNotFoundException e) {
            System.err.println("Error writing to file: " + e);
            return false;
        }

        return true;
    }




};