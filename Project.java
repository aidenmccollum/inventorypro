
/*********************************** 
* Filename: Project.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A class to represent and manage project information, including
*         project details, manager assignment, and associated items.
*
* Attributes:
* - projectName: String
* - projectDescription: String
* - managerID: String
* - items: ArrayList<Item>
*
* Methods:
* + Project(String name, String descr, String id, ArrayList<Item> projectItems): Constructor
* + getProjectName(): String
* + setProjectName(String projectName): void
* + getProjectDescription(): String
* + setProjectDescription(String projectDescription): void
* + getManager(): String
* + assignManager(String managerID): void
* + getItems(): ArrayList<Item>
* + setItems(ArrayList<Item> items): void
***********************************/


import java.util.ArrayList;

public class Project {
    private String projectName = "";
    private String projectDescription = "";
    private String managerID = "";
    private ArrayList<Item> items = new ArrayList<>();

    public Project(String name, String descr, String id, ArrayList<Item> projectItems){
        projectName = name;
        projectDescription = descr;
        managerID = id;
        items = projectItems;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getManager() {
        return managerID;
    }

    public void assignManager(String managerID) {
        this.managerID = managerID;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
};