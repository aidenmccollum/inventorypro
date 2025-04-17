
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
* + <<constructor>> Project(String, String, String, ArrayList<Item>)
* + getProjectName(): String
* + setProjectName(String): void
* + getProjectDescription(): String
* + setProjectDescription(String): void
* + getManager(): String
* + assignManager(String): void
* + getItems(): ArrayList<Item>
* + getItemString(): String
* + setItems(ArrayList<Item>): void
* + toString(): String
***********************************/


import java.util.ArrayList;

public class Project {
    private String projectName = "";
    private String projectDescription = "";
    private String managerID = "";
    private ArrayList<String> items = new ArrayList<>();

    public Project(String name, String descr, String id, ArrayList<String> projectItems){
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

    public ArrayList<String> getItems() {
        return items;
    }

    public String getItemString() {
        String itemString = "";
        for (String item : items) {
            if (itemString.isEmpty()) {
                itemString = item;
            } else {
                itemString += "," + item;
            }
            
        }

        return itemString;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "   Project Name: " + projectName + 
               "\n     Description: " + projectDescription + 
               "\n     Manager ID: " + managerID + 
               "\n     Item IDs: " + getItemString();
    }
};