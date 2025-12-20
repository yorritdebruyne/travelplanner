package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private String title, destination, description;
    private LocalDate startDate, endDate;
    private List<ItineraryItem> itineraryItems = new ArrayList<>();
    private List<Traveller> travellers = new ArrayList<>();
    private double totalPrice = 0;


    public Trip(String title, String destination, String description, String startDate, String endDate) {
        this.title = title;
        this.destination = destination;
        this.description = description;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }




    // Getters
    public String getTitle() {
        return title;
    }
    public String getDestination() {
        return destination;
    }
    public LocalDate getStringStartDate() {
        return startDate;
    }
    public LocalDate getStringEndDate() {
        return endDate;
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

    public void addTraveller(Traveller traveller) {
        travellers.add(traveller);
    }

    public void removeTraveller(Traveller traveller) {
        travellers.remove(traveller);
    }

    public List<Traveller> getTravellers() {
        return new ArrayList<>(travellers);
    }

    public double getTotalPrice() {return totalPrice;}

    @Override
    public String toString() {
        return title + " (" + destination + ")";
    }
    public String getDetails() {
        return "Title: " + title +
                "\nDestination: " + destination +
                "\nDescription: " + description +
                "\nStart date: " + startDate +
                "\nEnd date: " + endDate +
                "\nTravellers: " + travellers.size() +
                "\nActivities: " + itineraryItems.size() +
                "\nTotal price: €" + getTotalPrice();
    }
}
