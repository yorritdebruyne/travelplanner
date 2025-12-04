package Manager;

import Model.ItineraryItem;
import Model.Trip;
import Observer.TripObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class for managing all trips
 */
public class TripManager {

    // Volatile for thread safety
    private static volatile TripManager instance;
    private List<Trip> trips;
    private List<TripObserver> observers;

    private TripManager() {
        trips = new ArrayList<>();
        observers = new ArrayList<>();
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

    // Observer methods
    public void registerObserver(TripObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TripObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (TripObserver observer : observers) {
            observer.update(getAllTrips()); // Always send a copy of the list
        }
    }


    // CRUD methods
    public void addTrip(Trip trip) {
        trips.add(trip);
        notifyObservers();
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
        notifyObservers();
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
        return new ArrayList<>(trips); // Always return a copy of the list
    }
}
