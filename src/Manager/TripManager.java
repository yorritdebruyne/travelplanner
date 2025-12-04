package Manager;

import Model.ItineraryItem;
import Model.Trip;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class for managing all trips
 */
public class TripManager {

    // Volatile for thread safety
    private static volatile TripManager instance;
    private List<Trip> trips;

    private TripManager() {
        trips = new ArrayList<>();
    }

    // Double-checked locking Singleton
    public static TripManager getInstance() {
        if (instance == null) {                    // First check
            synchronized (TripManager.class) {     // Lock on class
                if (instance == null) {            // Second check
                    instance = new TripManager();  // Create Singleton
                }
            }
        }
        return instance;
    }

    // CRUD methods
    public void addTrip(Trip trip) {
        trips.add(trip);
        // TODO: add notifyObservers();
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
        // TODO: add notifyObservers();
    }

    public Trip getTripByTitle(String title) {
        for (Trip trip : trips) {
            if (trip.getTitle().equals(title)) {
                return trip;
            }
        }
        return null;
    }

    public List<Trip> getAllTrips() {
        return new ArrayList<>(trips); // Copy
    }
}
