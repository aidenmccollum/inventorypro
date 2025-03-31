/***********************************
* Filename: InventoryPro.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A class to manage an inventory system with user authentication,
*         item tracking, and project management capabilities.
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
* + runApp(Scanner scanner): void
* + promptAuth(Scanner scanner): boolean
* + login(Scanner scanner): boolean
* + signup(Scanner scanner): boolean
* + showMenu(Scanner scanner): void
* - handleUserChoice(Scanner scanner, int choice): void
* + addItem(): void
* + findItem(): void
* + listItems(): void
* + listProducts(): void
* + createProject(): void
* + findProject(): void
* + listUserProjects(): void
***********************************/





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
        } catch (Exception e) {
            System.err.println("Failed to parse int" + e);
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

        currentUser = userDB.signup(id, name, password);

        if (currentUser == null) {
            return false;
        } else {
            return  true;
        }
    };
    
    public void showMenu(Scanner scanner) {
        System.out.println("\n=== InventoryPro: Inventory Management System ===");
        System.out.println("1. Add an item");
        System.out.println("2. Find an item");
        System.out.println("3. List all items");
        System.out.println("4. List all products");
        System.out.println("5. Create a project");
        System.out.println("6. Find project by ID");
        System.out.println("7. Find my projects");
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

    private void handleUserChoice(Scanner scanner, int choice) {
        switch (choice) {
            case 1:
                // Call appropriate function
                System.out.println("Selected Choice 1");
                break;
            case 2:
                // Call appropriate function
                System.out.println("Selected Choice 2");
                break;
            case 3:
                // Call appropriate function
                System.out.println("Selected Choice 3");
                break;
            case 4:
                // Call appropriate function
                System.out.println("Selected Choice 4");
                break;
            case 5:
                // Call appropriate function
                System.out.println("Selected Choice 5");
                break;
            case 6:
                // Call appropriate function
                System.out.println("Selected Choice 6");
                break;
            case 7:
                // Call appropriate function
                System.out.println("Selected Choice 7");
                break;
            case 99:
                System.out.println("Exit selected");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                showMenu(scanner);
        }
    }

    public void addItem() {

    };

    public void findItem() {

    };

    public void listItems() {

    };

    public void listProducts() {

    };

    public void createProject() {

    };

    public void findProject() {

    };

    public void listUserProjects() {

    };
    
}