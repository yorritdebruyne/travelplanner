package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Trip {
    private String title, destination, description, stringStartDate, stringEndDate;
    private List<ItineraryItem> itineraryItems = new ArrayList<>();
    private double totalPrice = 0.0;

    public Trip(String title, String destination, String description, String stringStartDate, String stringEndDate) {
        this.title = title;
        this.destination = destination;
        this.description = description;
        this.stringStartDate = stringStartDate;
        this.stringEndDate = stringEndDate;
    }

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

    public double getTotalPrice() {
        return totalPrice;
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
