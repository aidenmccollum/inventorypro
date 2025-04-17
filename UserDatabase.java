


/*********************************** 
* Filename: UserDatabase.java
* Author: Aiden McCollum
* Created: March 28th, 2024
* Purpose: A class to manage a database of users
*
* Attributes:
* - users: ArrayList<User> 
*
* Methods:
* + <<constructor>> UserDatabase()
* + fetchUserFileData(): void 
* + setUserFileData(): boolean
* + login(String, String): User
* + signup(String, String, String): User
* + validateUserID(String): boolean
* + validateAccountCreation(String, String, String): String
***********************************/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDatabase {
    private ArrayList<User> users = new ArrayList<>();

    public UserDatabase() {
        //fetch the data from the database
        fetchUserFileData();
    }

    public void fetchUserFileData() {
        try {
            Scanner infile = new Scanner(new File("usersData.csv"));
            infile.nextLine(); //eat up the header line
            while(infile.hasNext()) {
                String line = infile.nextLine();
                String[] pieces = line.split(",");

                if (pieces.length != 3) {
                    continue; //skip the line
                }try{
                    String id = pieces[0];
                    String name = pieces[1];
                    String password = pieces[2];
                    User newUser = new User(id, name, password);
                    users.add(newUser);
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

    public boolean setUserFileData() {
        try {
            PrintWriter outfile = new PrintWriter(new File("usersData.csv"));
            outfile.append("ID,name,password");
            for (User user: users) {
                outfile.append("\n" + user.getID() + "," + user.getName() + "," + user.getPassword());
            }
            outfile.close();
        } catch(FileNotFoundException e) {
            System.err.println("Error writing to file: " + e);
            return false;
        }

        return true;
    }

    public User login(String id, String pass) {
        for (User user: users) {
            if (user.getID().equalsIgnoreCase(id) && user.getPassword().equals(pass)) {
                return user;
            }
        }

        return null;
    }

    public User signup(String id, String name, String password) {
        User newUser = new  User(id, name, password);
        users.add(newUser);

        setUserFileData();

        return  newUser;
    }


    public boolean validateUserID(String id) {
        for (User user: users) {
            if (user.getID().equalsIgnoreCase(id)) {
                return true;
            }
        }

        return false;
    }

    public String validateAccountCreation(String id, String name, String password) {
        // Check if ID already exists
        if (validateUserID(id)) {
            return "Error: Employee ID already exists";
        }

        // Check name length
        if (name.length() <= 2) {
            return "Error: Name must be longer than 2 characters";
        }

        // Check password length
        if (password.length() < 6) {
            return "Error: Password must be at least 6 characters";
        }

        // Check for special character and number
        boolean hasSpecial = password.matches(".*[!@#$%^&*()].*");
        boolean hasNumber = password.matches(".*\\d.*");

        if (!hasSpecial || !hasNumber) {
            return "Error: Password must contain at least one special character and one number";
        }

        return "valid";
    }

    
}