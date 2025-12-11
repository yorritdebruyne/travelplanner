package Observer;

import Model.Traveller;

import java.util.List;

public interface TravellerObserver {
    void update(List<Traveller> travellers);
}
