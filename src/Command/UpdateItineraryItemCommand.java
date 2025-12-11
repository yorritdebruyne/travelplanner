package Command;
//test
import Manager.*;
import Model.ItineraryItem;
import Model.Trip;
public class UpdateItineraryItemCommand implements Command {
    private final ItineraryItem oldItem, newItem;
    private ItineraryItemManager itemManager;

    public UpdateItineraryItemCommand(ItineraryItemManager itemManager, ItineraryItem oldItem, ItineraryItem newItem) {
        this.itemManager = itemManager;
        this.oldItem = oldItem;
        this.newItem = newItem;
    }

    @Override
    public void execute(){
            itemManager.removeItem(oldItem);
            itemManager.addItem(newItem);
    }

    @Override
    public void undo(){
            itemManager.removeItem(newItem);
            itemManager.addItem(oldItem);
    }
}


// TODO :   Trip trip = tripManager.getTripByTitle(tripTitle);
//        if (trip != null)     => fix this