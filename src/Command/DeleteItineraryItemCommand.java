package Command;

import Manager.ItineraryItemManager;
import Manager.TripManager;
import Model.ItineraryItem;
import Model.Trip;

public class DeleteItineraryItemCommand implements Command {
    private Trip trip;
    private ItineraryItem item;

    public DeleteItineraryItemCommand(Trip trip, ItineraryItem item) {
        this.trip = trip;
        this.item = item;
    }

    @Override
    public void execute(){
            trip.removeItineraryItem(item);
    }

    @Override
    public void undo() {
            trip.addItineraryItem(item);
    }
}
