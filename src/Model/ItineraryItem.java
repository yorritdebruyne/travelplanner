package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//test
public class ItineraryItem {
    String title, stringStartTime, stringEndTime, type, location, description;
    private List<ItineraryItem> itineraryItems = new ArrayList<>();
    private double totalPrice = 0.0;

    public ItineraryItem(String title, String stringStartTime, String stringEndTime, String type, String location, String description, double price) {
        this.title = title;
        this.stringStartTime = stringStartTime;
        this.stringEndTime = stringEndTime;
        this.type = type;
        this.location = location;
        this.description = description;
        this.totalPrice = price;
    }

    public String getStringStartTime() {return stringStartTime;}
    public double getPrice() {return totalPrice;}


    public List<ItineraryItem> getItineraryItems() {
        return new ArrayList<>(itineraryItems);
    }

    public String getTitle() {
        return title;
    }

    public String getStringEndTime() {
        return stringEndTime;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return title + " ( Start time: " + stringStartTime + ", End time " + stringEndTime + ")" ;
    }

    public String getDetails() {
        return "Title: " + title +
                "\nType: " + type +
                "\nLocation: " + location +
                "\nStart: " + stringStartTime +
                "\nEnd: " + stringEndTime +
                "\nDescription: " + description +
                "\nPrice: €" + totalPrice;
    }

}
