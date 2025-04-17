

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
* + <<constructor>> ProjectDatabase(ArrayList<Project>)
* + addProject(Project): void
* + setProjectFileData(): boolean
* + fetchProjectFileData(): void
* + parseProjectData(String[]): Project
* + findProjectByName(String): Project
* + getUserProjects(String): ArrayList<Project>
***********************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjectDatabase {
    private ArrayList<Project> projects = new ArrayList<>();

    public ProjectDatabase() {
        fetchProjectFileData();
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public boolean setProjectFileData() {
        try {
            PrintWriter outfile = new PrintWriter(new File("projectsData.csv"));
            outfile.append("name,descr,managerID,items");
            for (Project project: projects) {
                outfile.append("\n" + project.getProjectName() + "," + project.getProjectDescription() + "," + project.getManager() + project.getItemString());
            }
            outfile.close();
        } catch(FileNotFoundException e) {
            System.err.println("Error writing to file: " + e);
            return false;
        }

        return true;
    }

    public void fetchProjectFileData() {
        try {
            Scanner infile = new Scanner(new File("projectsData.csv"));
            infile.nextLine(); //eat up the header line
            while(infile.hasNext()) {
                String line = infile.nextLine();
                String[] pieces = line.split(",");

                if (pieces.length < 3) {
                    continue; //skip the line
                }try{
                    Project newProject = parseProjectData(pieces);
                    projects.add(newProject);
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

    public Project parseProjectData(String[] pieces) {
        String name = pieces[0];
        String description = pieces[1];
        String managerID = pieces[2];
        
        ArrayList<String> items = new ArrayList<String>();
        for(int i = 3; i < pieces.length; i++) {
            items.add(pieces[i]);
        }

        return new Project(name, description, managerID, items);
    }


    public Project findProjectByName(String name) {
        for (Project project : projects) {
            if (project.getProjectName().equals(name)) {
                return project;
            }
        }
        return null;
    }


    public ArrayList<Project> getUserProjects(String managerID) {
        ArrayList<Project> userProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getManager().equals(managerID)) {
                userProjects.add(project);
            }
        }
        return userProjects;
    }




};