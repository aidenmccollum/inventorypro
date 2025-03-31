

/*********************************** 
* Filename: ProjectDatabase.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A class to manage a database of projects, providing functionality
*         for adding, removing, and managing projects in the collection.
*
* Attributes:
* - projects: ArrayList<Project>
*
* Methods:
* +ProjectDatabase(ArrayList<Project> projects): Constructor
* +addProject(Project project): void
* +removeProject(Project project): void
* +findProjectByName(String name): Project
***********************************/


import java.util.ArrayList;

public class ProjectDatabase {
    private ArrayList<Project> projects = new ArrayList<>();

    public ProjectDatabase(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    // public Project findProjectByName(String name) {

    // }


};