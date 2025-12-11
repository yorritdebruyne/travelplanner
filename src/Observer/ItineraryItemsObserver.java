package Observer;

import Model.*;

import java.util.List;

public interface ItineraryItemsObserver {
    void update(List<ItineraryItem> items);
}
