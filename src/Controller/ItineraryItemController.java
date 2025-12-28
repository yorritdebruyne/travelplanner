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

    public ItineraryItem getItemByTitle(String title) {
        return itemManager.getItemByTitle(title);
    }

    public List<ItineraryItem> getAllItems(){
        return itemManager.getAllItems();
    }

    public boolean updateItem(Trip trip, ItineraryItem oldItem, ItineraryItem newItem){
        if(oldItem != null) {
            commandManager.executeCommand(new UpdateItineraryItemCommand(trip, oldItem, newItem));
            return true;
        }
        return false;
    }

    // Undo last command
    public void undoCommand() {
        commandManager.undoCommand();
    }

    // Redo last undone command
    public void redoCommand() {
        commandManager.redoCommand();
    }
}
