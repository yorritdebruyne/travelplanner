package Observer;

import Model.Trip;

import java.util.List;

public interface TripObserver {
    void update(List<Trip> trips);
}
