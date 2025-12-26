package Command;
//test
import Manager.*;
import Model.ItineraryItem;
import Model.Trip;
public class UpdateItineraryItemCommand implements Command {
    private final ItineraryItem oldItem, newItem;
    private Trip trip;

    public UpdateItineraryItemCommand(Trip trip, ItineraryItem oldItem, ItineraryItem newItem) {
        this.trip = trip;
        this.oldItem = oldItem;
        this.newItem = newItem;
    }

    @Override
    public void execute(){
            trip.removeItineraryItem(oldItem);
            trip.addItineraryItem(newItem);
    }

    @Override
    public void undo(){
            trip.removeItineraryItem(newItem);
            trip.addItineraryItem(oldItem);
    }
}

