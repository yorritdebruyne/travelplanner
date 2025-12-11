package Command;
//test
import Manager.TripManager;
import Model.ItineraryItem;
import Model.Trip;
public class UpdateItineraryItemCommand implements Command {
    private final String tripTitle;
    private final ItineraryItem oldItem, newItem;
    private final TripManager tripManager;

    public UpdateItineraryItemCommand(String tripTitle, ItineraryItem oldItem, ItineraryItem newItem, TripManager tripManager) {
        this.tripTitle = tripTitle;
        this.oldItem = oldItem;
        this.newItem = newItem;
        this.tripManager = TripManager.getInstance();
    }

    @Override
    public void execute(){
        Trip trip = tripManager.getTripByTitle(tripTitle);
        if (trip != null) {
            trip.removeItineraryItem(oldItem);
            trip.addItineraryItem(newItem);
        }
    }

    @Override
    public void undo(){
        Trip trip = tripManager.getTripByTitle(tripTitle);
        if (trip != null) {
            trip.removeItineraryItem(newItem);
            trip.addItineraryItem(oldItem);
        }
    }
}
