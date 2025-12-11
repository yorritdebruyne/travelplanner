package View;

import Model.Trip;
import Observer.TripObserver;

import java.util.List;

public class TripListView implements TripObserver {

    @Override
    public void update(List<Trip> trips) {
        System.out.println("Updated trip list:");
        for (Trip trip : trips) {
            System.out.println("- " + trip.getTitle());
        }
    }
}
