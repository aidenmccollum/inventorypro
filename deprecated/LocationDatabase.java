
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LocationDatabase {
    private ArrayList<Location> locations = new ArrayList<>();
    
    // Constructor
    public LocationDatabase() {
    }
    
    // Add a new location to the database
    public void addLocation(Location location) {
        locations.add(location);
    }
    
    // Remove a location from the database
    public void removeLocation(Location location) {
        locations.remove(location);
    }
    
    // Get all locations
    public ArrayList<Location> getLocations() {
        return locations;
    }
    
    // Find location by name
    public Location findLocationByName(String name) {
        for (Location location : locations) {
            if (location.getLocation().equalsIgnoreCase(name)) {
                return location;
            }
        }
        return null;
    }

    public String getItemsString(Location location) {
        String itemString = "";
        for (String item : location.getStoredItems()) {
            itemString += "," + item;
        }

        return itemString;
    }

    public boolean setLocationFileData() {
        try {
            PrintWriter outfile = new PrintWriter(new File("locationsData.csv"));
            outfile.append("locationName,storedItem");
            for (Location location: locations) {
                outfile.append("\n" + location.getLocation() +getItemsString(location));
            }
            outfile.close();
        } catch(FileNotFoundException e) {
            System.err.println("Error writing to file: " + e);
            return false;
        }
        return true;
    }

    public void fetchLocationFileData() {
        try {
            Scanner infile = new Scanner(new File("locationsData.csv"));
            infile.nextLine(); //eat up the header line
            while(infile.hasNext()) {
                String line = infile.nextLine();
                String[] pieces = line.split(",");

                if (pieces.length < 2) {
                    continue; //skip the line
                }
                try {
                    String locationName = pieces[0];

                    ArrayList<String> items = new ArrayList<String>();
                    for(int i = 1; i < pieces.length; i++) {
                        items.add(pieces[i]);
                    }

                    Location newLocation = new Location(locationName, items);
                    locations.add(newLocation);
                }
                catch(Exception e) {
                    //skip the line
                    
                }
            }
            infile.close();
        } catch(FileNotFoundException e) {
            System.err.println("Couldn't find the file: " + e);
        }
    }
}
