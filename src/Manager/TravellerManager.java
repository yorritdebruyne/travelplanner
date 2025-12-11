package Manager;


import Model.*;
import Observer.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravellerManager {

    // Volatile for thread safety
    private static volatile TravellerManager instance;
    private final Map<String, Traveller> travellers = new HashMap<>();
    private final List<TravellerObserver> observers = new ArrayList<>();

    private TravellerManager() {}

    // Double-checked locking Singleton
    public static TravellerManager getInstance() {
        if (instance == null) {
            synchronized (TravellerManager.class) {
                if (instance == null) {
                    instance = new TravellerManager();
                }
            }
        }
        return instance;
    }

    // Observer methods
    public void registerObserver(TravellerObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TravellerObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (TravellerObserver observer : observers) {
            observer.update(getAllTravellers()); // Always send a copy of the list
        }
    }

    // CRUD
    public void addTraveller(Traveller traveller) {
        travellers.put(traveller.getId(), traveller);
        notifyObservers();
    }

    public void removeTraveller(String travellerId) {
        travellers.remove(travellerId);
        notifyObservers();
    }

    public Traveller getTravellerById(String id) {
        return travellers.get(id);
    }

    public List<Traveller> getAllTravellers() {
        return new ArrayList<>(travellers.values());
    }
}
