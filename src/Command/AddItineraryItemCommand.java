package Command;

import Manager.*;
import Model.ItineraryItem;
import Model.Trip;
//test
public class AddItineraryItemCommand implements Command{
    private Trip trip;
    private ItineraryItem item;

    public AddItineraryItemCommand(Trip trip, ItineraryItem item) {
        this.trip = trip;
        this.item = item;
    }

    @Override
    public void execute() {
        trip.addItineraryItem(item);
    }

    @Override
    public void undo() {
        trip.removeItineraryItem(item);
    }
}
