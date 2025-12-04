package Controller;

import Model.Trip;
import Model.TripBuilder;
import Manager.TripManager;

import java.util.List;

public class TripController {

    private TripManager tripManager;

    // Retrieve Singleton instance
    public TripController() {
        this.tripManager = TripManager.getInstance();
    }

    // CREATE a trip via TripBuilder
    public Trip createTrip(String title, String destination, String description, String stringStartDate, String stringEndDate) {
        Trip trip = new TripBuilder()
                .setTitle(title)
                .setDestination(destination)
                .setDescription(description)
                .setStringStartDate(stringStartDate)
                .setStringEndDate(stringEndDate)
                .build();
        tripManager.addTrip(trip);
        return trip;
    }

    // READ all trips
    public Trip getTripByTitle(String title) {
        return tripManager.getTripByTitle(title);
    }

    public List<Trip> getAllTrips() {
        return tripManager.getAllTrips();
    }

    // UPDATE a trip via TripBuilder
    public boolean updateTrip(String oldTitle, String oldDestination, String oldDescription, String oldStringStartDate, String oldStringEndDate
    , String newTitle, String newDestination, String newDescription, String newStringStartDate, String newStringEndDate) {
        Trip trip = tripManager.getInstance().getTripByTitle(oldTitle);
        if (trip != null) {
            Trip updatedTrip = new TripBuilder()
                    .setTitle(newTitle)
                    .setDestination(newDestination)
                    .setDescription(newDescription)
                    .setStringStartDate(newStringStartDate)
                    .setStringEndDate(newStringEndDate)
                    .build();

            tripManager.removeTrip(trip);
            tripManager.addTrip(updatedTrip);
            return true;
        }
        return false;
    }

    // DELETE a trip
    public boolean deleteTrip(String title) {
        Trip trip = tripManager.getTripByTitle(title);
        if (trip != null) {
            tripManager.removeTrip(trip);
            return true;
        }
        return false;
    }
}
