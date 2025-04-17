
/*********************************** 
* Filename: User.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A class to manage user accounts and their associated projects
*
* Attributes:
* - employeeID: String
* - name: String
* - password: String
* - projects: ArrayList<Project>
*
* Methods:
* + <constructor>> User(String, String, String)
* + getID(): String
* + setID(String): void
* + getName(): String
* + setName(String): void
* + getPassword(): String
* + setPassword(String): void
* + findProjects(): ArrayList<Project>
* + addProject(Project): void
***********************************/


import java.util.ArrayList;

public class User {
    private String employeeID = "";
    private String name = "";
    private String password = "";
    private ArrayList<Project> projects = new ArrayList<>();

    public User(String id, String userName, String password) {
        employeeID = id;
        name = userName;
        this.password = password;
    }

    public String getID() {
        return employeeID;
    }

    public void setID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Project> findProjects() {
        return projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    
}