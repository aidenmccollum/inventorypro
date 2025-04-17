/***********************************
* Filename: InventoryPro.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A class to manage an inventory system with user authentication,
*         item tracking, and project management capabilities. This is the 
*         main class of the program.
*
* Attributes:
* - authenticated: boolean
* - currentUser: User
* - itemDB: ItemDatabase
* - userDB: UserDatabase
* - projectDB: ProjectDatabase
*
* Methods:
* + main(String[] args): void
* + runApp(Scanner): void
* + promptAuth(Scanner): boolean
* + login(Scanner): boolean
* + signup(Scanner): boolean
* + showMenu(Scanner): void
* + handleUserChoice(Scanner, int): void
* + addItem(Scanner): void
* + promptForString(Scanner, String): String
* + promptForStatus(Scanner): String
* + addProduct(Scanner, String, float, int, String, String, String): void
* + addExpense(Scanner, String, float, int, String, String, String): void
* + findItem(Scanner): void
* + editItem(Scanner, Item): void
* + editBasicAttributes(Scanner, Item): void
* + editProductAttributes(Scanner, Product): void
* + editExpenseAttributes(Scanner, Expense): void
* + listItems(Scanner): void
* + listProducts(Scanner): void
* + createProject(Scanner): void
* + addProjectItems(Scanner): ArrayList<String>
* + findProject(Scanner): void
* + listUserProjects(Scanner): void
* + findLocation(Scanner): void
* + displayItemsAtLocation(String): void
***********************************/





import java.util.ArrayList;
import java.util.Scanner;

public class InventoryPro {

    private boolean authenticated = false;
    private User currentUser = null;
    public ItemDatabase itemDB = null;
    public UserDatabase userDB = null;
    public ProjectDatabase projectDB = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryPro app = new InventoryPro();
        app.runApp(scanner);
        scanner.close();
    }

    public void runApp(Scanner scanner) {
        //initiallizing the databases
        userDB = new UserDatabase();
        itemDB = new ItemDatabase();
        projectDB = new ProjectDatabase();

        authenticated = promptAuth(scanner);

        //rest of program must run inside this statement
        if (authenticated == true) {
            System.out.println("\n\n\nWelcome back, " + currentUser.getName() + "!");
            showMenu(scanner);
        } else {
            System.out.println("failed to authenticate.");
        }
    }

    public boolean promptAuth(Scanner scanner) {
        System.out.println("\n=== InventoryPro: Inventory Management System ===");
        System.out.print("Welcome! Would you like to (1) log in or (2) sign up: ");

        String selectionStr = scanner.nextLine();
        int selection = 0;
        boolean authed = false;
    
        try {
            selection = Integer.parseInt(selectionStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Failed to parse int" + e);
        }

        if (selection == 1) {
            authed = login(scanner);
        } else if (selection == 2) {
            authed = signup(scanner);
        } else {
            System.out.println("Failed to authenticate! Try again later.");
        }
        
        return authed;
    }

    public boolean login(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        currentUser = userDB.login(id, password);

        if (currentUser == null) {
            return false;
        } else {
            return  true;
        }
    };

    public boolean signup(Scanner scanner) {

        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Your Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String response = userDB.validateAccountCreation(id, name, password);

        if (response.equalsIgnoreCase("valid")) {
            currentUser = userDB.signup(id, name, password);

            if (currentUser != null) {
                return true;
            }
        } else {
            System.err.println(response);
        }
        
        return  false;
    
    };
    
    public void showMenu(Scanner scanner) {
        System.out.println("\n=== InventoryPro: Inventory Management System ===");
        System.out.println("1. Add an item");
        System.out.println("2. Find an item");
        System.out.println("3. List all items");
        System.out.println("4. List all products");
        System.out.println("5. Create a project");
        System.out.println("6. Find project by name");
        System.out.println("7. Find my projects");
        System.out.println("8. Find a location");
        System.out.println("99. Exit");
        System.out.print("Enter your choice: ");

        String choiceStr = scanner.nextLine();

        int choice = 0;
        try {
            choice = Integer.parseInt(choiceStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            showMenu(scanner);
        }


        handleUserChoice(scanner, choice);
    }

    public void handleUserChoice(Scanner scanner, int choice) {
        switch (choice) {
            case 1:
                System.out.println("_____________________________________");
                addItem(scanner);
                break;
            case 2:
                System.out.println("_____________________________________");
                findItem(scanner);
                break;
            case 3:
                System.out.println("_____________________________________");
                listItems(scanner);
                break;
            case 4:
                System.out.println("_____________________________________");
                listProducts(scanner);
                break;
            case 5:
                System.out.println("_____________________________________");
                createProject(scanner);
                break;
            case 6:
                System.out.println("_____________________________________");
                findProject(scanner);
                break;
            case 7:
                System.out.println("_____________________________________");
                listUserProjects(scanner);
                break;
            case 8:
                System.out.println("_____________________________________");
                findLocation(scanner);

            case 99:
                System.out.println("Goodbye!!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                showMenu(scanner);
        }
    }

    public void addItem(Scanner scanner) {
        
        Object[] itemBasics = getItemBasics(scanner);

        if (((String)itemBasics[6]).equalsIgnoreCase("p")) {
            addProduct(scanner, (String)itemBasics[0], (float)itemBasics[1], (int)itemBasics[2], (String)itemBasics[3], (String)itemBasics[4], (String)itemBasics[5]);
        } else {
            addExpense(scanner, (String)itemBasics[0], (float)itemBasics[1], (int)itemBasics[2], (String)itemBasics[3], (String)itemBasics[4], (String)itemBasics[5]);
        }

        itemDB.setItemFileData();
        showMenu(scanner);

    };

    public Object[] getItemBasics(Scanner scanner) {
        String itemName = promptForString(scanner, "item name");

        String unitCostStr = promptForString(scanner, "unit cost");
        float unitCost;
        try {
            unitCost = Float.parseFloat(unitCostStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid quantity: must be a float");
        }
        

        String quantityStr = promptForString(scanner, "quantity");
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new NumberFormatException("Quantity must be positive");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid quantity: must be a positive number");
        }

        String supplier = promptForString(scanner, "supplier");

        String status = promptForStatus(scanner);

        String locationName = "None";
        if (status.equals("Arrived") || status.equals("In Stock")) {
            String rawName = promptForString(scanner, "item location");
            if (!rawName.trim().equals("")) {
                locationName = rawName;
            }
        }

        String typeStr = "";
        while (!typeStr.equalsIgnoreCase("p") && !typeStr.equalsIgnoreCase("e")) {
            System.out.print("is this item a product (p) or an expense (e): ");
            typeStr = scanner.nextLine().trim();
        }

        return new Object[] {itemName, unitCost, quantity, supplier, status, locationName, typeStr};
    }

    public String promptForString(Scanner scanner, String valueName) {
        System.out.print("Enter "+ valueName + ": ");
        String value = scanner.nextLine();
        return value;
    }

    public String promptForStatus(Scanner scanner) {
        String status = null;

        while (status == null) {
            System.out.print("Enter the status of the item: ");
            String rawStatus = scanner.nextLine().trim();

            status = itemDB.validateStatus(rawStatus);
            if(status == null){
                System.out.println("Invalid status. Status must be one of the following: Purchased, In Transit, Arrived, In Stock, Out of Stock");
            }
        }

        return status;
    }

    public void addProduct(Scanner scanner, String itemName, float unitCost, int quantity, String supplier, String status, String location) {
        String salePriceStr = promptForString(scanner, "sale price");
        float salePrice;
        try {
            salePrice = Float.parseFloat(salePriceStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Value entered for sale price was not a valid Float");
        }
        

        String customer = promptForString(scanner, "customer");
        String id = itemDB.getUID("p");
        Product newProduct = new Product(id, itemName, unitCost, quantity, supplier, status, location, salePrice, customer, false);
        itemDB.addItem(newProduct);
    }

    public void addExpense(Scanner scanner, String itemName, float unitCost, int quantity, String supplier, String status, String location) {
        String expenseType = promptForString(scanner, "expense type");

        String daysStr = promptForString(scanner, "item timespan");
        int daysLeft;
        try {
            daysLeft = Integer.parseInt(daysStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Item timespan value entered was not a valid integer" + e);
        }
        
        String id = itemDB.getUID("e");

        Expense newExpense = new Expense(id, itemName, unitCost, quantity, supplier, status, location, expenseType, daysLeft);
        itemDB.addItem(newExpense);
    }

    public void findItem(Scanner scanner) {
        String itemId = "";
        Item foundItem = null;

        while (foundItem == null) {
            System.out.print("Enter item ID: ");
            itemId = scanner.nextLine().trim();
            foundItem = itemDB.findItemByID(itemId);
            
            if (foundItem == null) {
                System.out.println("No item found with ID: " + itemId);
                System.out.print("Try again? (y/n): ");
                if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    break;
                }
            }
        }

        if (foundItem != null) {
            System.out.println("\nItem found:");
            System.out.println(foundItem.toString());

            System.out.print("Would you like to edit this item? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                editItem(scanner, foundItem);
            }
        }

        showMenu(scanner);

    };

    public void editItem(Scanner scanner, Item item) {

        System.out.println("\nEditing item " + item.getID());
        System.out.println("Press Enter to keep current value, or enter new value.");

        editBasicAttributes(scanner, item);

        if (item instanceof Product) {
            editProductAttributes(scanner, (Product) item);
        } else {
            editExpenseAttributes(scanner, (Expense) item);
        }
        

        itemDB.setItemFileData();
    }

    public void editBasicAttributes(Scanner scanner, Item item) {
         String input = promptForString(scanner, "name [Current value: " + item.getItemName() + "]");
        if (!input.trim().isEmpty()) {
            item.setItemName(input);
        }

        input = promptForString(scanner, "unit cost [Current value: " + item.getUnitCost() + "]");
        if (!input.trim().isEmpty()) {
            try {
                item.setUnitCost(Float.parseFloat(input)); 
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Failed to enter a valid float for unit cost");
            }
        }

        input = promptForString(scanner, "quantity [Current value: " + item.getQuantity() + "]");
        if (!input.trim().isEmpty()) {
            try {
               item.setQuantity(Integer.parseInt(input)); 
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Failed to enter valid integer for quantity");
            }
            
        }

        input = promptForString(scanner, "supplier [Current value: " + item.getSupplier() + "]");
        if (!input.trim().isEmpty()) {
            item.setSupplier(input);
        }

        input = promptForString(scanner, "status [Current value: " + item.getStatus() + "]");
        if (!input.trim().isEmpty()) {
            String validStatus = itemDB.validateStatus(input);
            if (validStatus != null) {
                item.setStatus(validStatus);
            }
        }

        if (item.getStatus().equals("Arrived") || item.getStatus().equals("In Stock")) {
            input = promptForString(scanner, "location [" + item.getLocationName() + "]");
            if (!input.trim().isEmpty()) {
                item.setLocationName(input);
            }
        } else {
            item.setLocationName("None");
        }
    }

    public void editProductAttributes(Scanner scanner, Product product) {
        String input = promptForString(scanner, "sale price [Current value: " + product.getSellingPrice() + "]");
        if (!input.trim().isEmpty()) {
            try {
                product.setSellingPrice(Float.parseFloat(input));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Value entered for sale price was not a valid float.");
            }
        }

        input = promptForString(scanner, "customer [Current value: " + product.getCustomer() + "]");
        if (!input.trim().isEmpty()) {
            product.setCustomer(input);
        }

        System.out.print("Is this item on sale? (y/n) [Current value: " + product.getSaleStatus() + "]: ");
        String saleInput = scanner.nextLine().trim();
        if (!saleInput.isEmpty()) {
            product.setSaleStatus(saleInput.equalsIgnoreCase("y"));
        }
    }

    public void editExpenseAttributes(Scanner scanner, Expense expense) {
        String input = promptForString(scanner, "expense type [Current value: " + expense.getExpenseType() + "]");
        if (!input.trim().isEmpty()) {
            expense.setExpenseType(input);
        }

        input = promptForString(scanner, "days left [Current value: " + expense.getTimespan() + "]");
        if (!input.trim().isEmpty()) {
            expense.setTimespan(Integer.parseInt(input));
        }
    }

    public void listItems(Scanner scanner) {
        System.out.println("\nAll Items in Inventory:");
        System.out.println("  ---------------");
        ArrayList<String> items = itemDB.listItems();
        if (items.isEmpty()) {
            System.out.println("No items found in inventory.");
        } else {
            for (String itemID : items) {
                Item item = itemDB.findItemByID(itemID);
                System.out.println(item.toString());
                System.out.println("  ---------------");
            }
        }
        showMenu(scanner);

    };

    public void listProducts(Scanner scanner) {
        System.out.println("\nAll Products in Inventory:");
        System.out.println("  ---------------");
        ArrayList<String> items = itemDB.listItems();
        if (items.isEmpty()) {
            System.out.println("No items found in inventory.");
        } else {
            for (String itemID : items) {
                Item item = itemDB.findItemByID(itemID);
                if (item instanceof Product) {
                    System.out.println(item.toString());
                    System.out.println("  ---------------");
                }
            }
        }
        showMenu(scanner);

    };

    public void createProject(Scanner scanner) {
        String projectName = promptForString(scanner, "project name");
        String projectDescr = promptForString(scanner, "project description");

        boolean validID = false;
        String managerID = "";

        while (!validID) {
            managerID = promptForString(scanner, "project manager employee ID");
            validID = userDB.validateUserID(managerID);

            if (!validID) {
                System.out.println("Invalid employee ID. Please try again.");
            }
        }

        ArrayList<String> itemIDs = addProjectItems(scanner);

        Project newProject = new Project(projectName, projectDescr, managerID, itemIDs);
        projectDB.addProject(newProject);

        projectDB.setProjectFileData();
        showMenu(scanner);

    };

    public ArrayList<String> addProjectItems(Scanner scanner) {
        boolean addItem = true;
        ArrayList<String> itemIDs = new ArrayList<>();
        while (addItem) {
            String itemID = promptForString(scanner, "item ID");
            if (itemDB.findItemByID(itemID) != null) {
                itemIDs.add(itemID);
            } else {
                System.out.println("Item not found with ID: " + itemID);
            }
            
            System.out.print("Add another item? (y/n): ");
            addItem = scanner.nextLine().trim().equalsIgnoreCase("y");
        }

        return itemIDs;
    }
    

    public void findProject(Scanner scanner) {
        String projectName = "";
        Project foundProject = null;

        while (foundProject == null) {
            System.out.print("Enter project name: ");
            projectName = scanner.nextLine().trim();
            foundProject = projectDB.findProjectByName(projectName);
            
            if (foundProject == null) {
                System.out.println("No project found with the name: " + projectName);
                System.out.print("Try again? (y/n): ");
                if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    break;
                }
            }
        }

        if (foundProject != null) {
            System.out.println("\nProject found:");
            System.out.println(foundProject.toString());
        }

        showMenu(scanner);

    };

    public void listUserProjects(Scanner scanner) {
        ArrayList<Project> userProjects = projectDB.getUserProjects(currentUser.getID());
        if (userProjects.isEmpty()) {
            System.out.println("No projects found for current user.");
        } else {
            System.out.println("\nYour Projects:");
            System.out.println("  ---------------");
            for (Project project : userProjects) {
                System.out.println(project.toString());
                System.out.println("  ---------------");
            }
        }
        showMenu(scanner);

    };

    public void findLocation(Scanner scanner) {
        System.out.print("Enter location name: ");
        String locationName = scanner.nextLine().trim();
        
        System.out.println("\nItems at location: " + locationName);
        System.out.println("  ---------------");
        displayItemsAtLocation(locationName);

        showMenu(scanner);
    }

    public void displayItemsAtLocation(String locationName) {
        boolean found = false;

        for (String itemId : itemDB.listItems()) {
            Item item = itemDB.findItemByID(itemId);
            if (item.getLocationName().equalsIgnoreCase(locationName)) {
                System.out.println(item.toString());
                System.out.println("  ---------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No items found at this location.");
        }
    }


    
}