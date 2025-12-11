package Command;

import Manager.ItineraryItemManager;
import Manager.TripManager;
import Model.ItineraryItem;
import Model.Trip;
//test
public class DeleteItineraryItemCommand implements Command {
    private final ItineraryItem item;
    private final ItineraryItemManager itemManager;

    public DeleteItineraryItemCommand(ItineraryItem item, ItineraryItemManager itemManager) {
        this.item = item;
        this.itemManager = itemManager;
    }

    @Override
    public void execute(){
            item.removeItineraryItem(item);
    }

    @Override
    public void undo() {
            item.addItineraryItem(item);
    }
}
