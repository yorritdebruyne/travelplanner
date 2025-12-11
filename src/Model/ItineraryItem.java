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

    public void addItineraryItem(ItineraryItem item) {
        itineraryItems.add(item);
        recalculateTotalPrice();
    }

    public void removeItineraryItem(ItineraryItem item){
        itineraryItems.remove(item);
        recalculateTotalPrice();
    }

    public void recalculateTotalPrice() {
        double sum =0.0;
        for(ItineraryItem item : itineraryItems){
            sum += item.getPrice();
        }
        this.totalPrice = sum;
    }

    public List<ItineraryItem> getItemsForDay(LocalDate day) {
        List<ItineraryItem> result = new ArrayList<>();
        for (ItineraryItem item : itineraryItems) {
            LocalDate itemDate = LocalDate.parse(item.getStringStartTime().substring(0, 10));
            if (itemDate.equals(day)) {
                result.add(item);
            }
        }
        return result;
    }

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

    public double getTotalPrice() {
        return totalPrice;
    }
}
