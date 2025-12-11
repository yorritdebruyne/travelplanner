package Command;

import Manager.*;
import Model.ItineraryItem;
import Model.Trip;
//test
public class AddItineraryItemCommand implements Command{
    private final String tripTitle;
    private final ItineraryItem item;
    private TripManager tripManager;
    private final ItineraryItemManager ItemManager;

    public AddItineraryItemCommand(String tripTitle, ItineraryItem item, ItineraryItemManager ItemManager) {
        this.tripTitle = tripTitle;
        this.item = item;
        this.ItemManager = ItemManager;
    }

    @Override
    public void execute() {
        Trip trip = tripManager.getTripByTitle(tripTitle);
        if (trip != null){
            item.addItineraryItem(item);
        }
    }

    @Override
    public void undo() {
        Trip trip = tripManager.getTripByTitle(tripTitle);
        if (trip != null){
            item.removeItineraryItem(item);
        }
    }
}
