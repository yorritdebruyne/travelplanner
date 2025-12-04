package Model;

public class Trip {
    private String title, destination, description, stringStartDate, stringEndDate;

    public Trip(String title, String destination, String description, String stringStartDate, String stringEndDate) {
        this.title = title;
        this.destination = destination;
        this.description = description;
        this.stringStartDate = stringStartDate;
        this.stringEndDate = stringEndDate;
    }

    // Getters
    public String getTitle() {
        return title;
    }
    public String getDestination() {
        return destination;
    }
    public String getStringStartDate() {
        return stringStartDate;
    }
    public String getStringEndDate() {
        return stringEndDate;
    }
}
