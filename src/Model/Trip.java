package Model;

import Manager.ItineraryItemManager;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String title, destination, description;
    private LocalDate startDate, endDate;
    private List<ItineraryItem> itineraryItems = new ArrayList<>();
    private List<Traveller> travellers = new ArrayList<>();
    private double totalPrice = 0;
    private PriceStrategy priceStrategy = new DefaultPriceStrategy();


    public Trip(String title, String destination, String description, String startDate, String endDate) {
        this.title = title;
        this.destination = destination;
        this.description = description;
        // Adjust to use dd/MM/YYYY
        this.startDate = LocalDate.parse(startDate, FORMATTER);
        this.endDate = LocalDate.parse(endDate, FORMATTER);
    }




    // Getters
    public String getTitle() {
        return title;
    }

    public LocalDate getStringStartDate() {
        return startDate;
    }
    public LocalDate getStringEndDate() {
        return endDate;
    }

    public void addItineraryItem(ItineraryItem item) {
        // Range check
        LocalDate itemDate = LocalDate.parse(item.getStringStartTime().substring(0, 10), FORMATTER);
        if (itemDate.isBefore(startDate) || itemDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Item date " + itemDate + " is outside trip range (" + startDate + " to " + endDate + ")");
        }
        itineraryItems.add(item);
        recalculateTotalPrice();
        ItineraryItemManager.getInstance().notifyObservers();
    }

    public void removeItineraryItem(ItineraryItem item){
        itineraryItems.remove(item);
        recalculateTotalPrice();
        ItineraryItemManager.getInstance().notifyObservers();
    }

    public void recalculateTotalPrice() {
        this.totalPrice = priceStrategy.calculate(itineraryItems);
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
