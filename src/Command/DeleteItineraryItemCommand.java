package Command;

import Manager.TripManager;
import Model.ItineraryItem;
import Model.Trip;

public class DeleteItineraryItemCommand implements Command {
    private final String tripTitle;
    private final ItineraryItem item;
    private final TripManager tripManager;

    public DeleteItineraryItemCommand(String tripTitle, ItineraryItem item) {
        this.tripTitle = tripTitle;
        this.item = item;
        this.tripManager = TripManager.getInstance();
    }

    @Override
    public void execute(){
        Trip trip = tripManager.getTripByTitle(tripTitle);
        if (trip != null) {
            trip.removeItineraryItem(item);
        }
    }

    @Override
    public void undo() {
        Trip trip = tripManager.getTripByTitle(tripTitle);
        if (trip != null) {
            trip.addItineraryItem(item);
        }
    }
}
