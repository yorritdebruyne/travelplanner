package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class ItineraryItem {
    protected String title, stringStartTime, stringEndTime, type, location, description;
    protected double price;

    public ItineraryItem(String title, String stringStartTime, String stringEndTime, String location, String description, double price) {
        this.title = title;
        this.stringStartTime = stringStartTime;
        this.stringEndTime = stringEndTime;
        this.location = location;
        this.description = description;
        this.price = price;
    }

    public String getStringStartTime() {return stringStartTime;}

    public double getPrice() {return price;}



    public String getTitle() {
        return title;
    }

//

    public abstract String getType();




    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return title + " ( Start time: " + stringStartTime + ", End time " + stringEndTime + ")" ;
    }

    public String getDetails() {
        return "Title: " + title +
                "\nType: " + getType() +
                "\nLocation: " + location +
                "\nStart: " + stringStartTime +
                "\nEnd: " + stringEndTime +
                "\nDescription: " + description +
                "\nPrice: €" + price;
    }

    public String getStringEndTime() {
        return stringEndTime;
    }
}
