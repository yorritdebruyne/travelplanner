package Model;

public class ItineraryItem {
    String title, stringStartTime, stringEndTime, type, location, description;
    double price;

    public ItineraryItem(String title, String stringStartTime, String stringEndTime, String type, String location, String description, double price) {
        this.title = title;
        this.stringStartTime = stringStartTime;
        this.stringEndTime = stringEndTime;
        this.type = type;
        this.location = location;
        this.description = description;
        this.price = price;
    }
}
