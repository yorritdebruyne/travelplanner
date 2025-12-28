package Controller;

import Command.*;
import Manager.*;
import Model.*;

import java.util.List;

public class ItineraryItemController {
    private ItineraryItemManager itemManager;
    private CommandManager commandManager;

    public ItineraryItemController(CommandManager commandManager) {
        this.itemManager = ItineraryItemManager.getInstance();
        this.commandManager = commandManager;
    }

    public void createItem(Trip trip, ItineraryItem item) {
        commandManager.executeCommand(new AddItineraryItemCommand(trip, item));
    }

    public void deleteItem(Trip trip, ItineraryItem item) {
        commandManager.executeCommand(new DeleteItineraryItemCommand(trip, item));
    }

}
