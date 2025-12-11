package Controller;

import Command.*;
import Manager.*;
import Model.*;

import java.util.List;

public class ItineraryItemController {
    private ItineraryItemManager itemManager;
    private CommandManager commandManager;

    // Retrieve Singleton instance
    public ItineraryItemController() {
        this.itemManager = ItineraryItemManager.getInstance();
        this.commandManager = new CommandManager();
    }

    public ItineraryItem createItem(String title, String stringStartTime, String stringEndTime, String type, String location, String description, double totalPrice){
        ItineraryItem ItineraryItem = new ItemBuilder()
                .setTitle(title)
                .setStringStartTime(stringStartTime)
                .setStringEndTime(stringEndTime)
                .setType(type)
                .setLocation(location)
                .setDescription(description)
                .setTotalPrice(totalPrice)
                .build();
        commandManager.executeCommand(new AddItineraryItemCommand(title,ItineraryItem, itemManager));
        return ItineraryItem;
    }

    public ItineraryItem getItemByTitle(String title) {
        return itemManager.getItemByTitle(title);
    }

    public List<ItineraryItem> getAllItems(){
        return itemManager.getAllItems();
    }

    public boolean updateItem(String oldTitle, String newTitle, String stringStartTime, String stringEndTime, String type, String location, String description, double totalPrice){
        ItineraryItem oldItem = getItemByTitle(oldTitle);
        if(oldItem != null) {
            ItineraryItem newItem = new ItemBuilder()
                    .setTitle(newTitle)
                    .setStringStartTime(stringStartTime)
                    .setStringEndTime(stringEndTime)
                    .setType(type)
                    .setLocation(location)
                    .setDescription(description)
                    .setTotalPrice(totalPrice)
                    .build();
            commandManager.executeCommand(new UpdateItineraryItemCommand(itemManager, oldItem, newItem));
            return true;
        }
        return false;
    }

    public boolean deleteItem(String title){
        ItineraryItem item = itemManager.getItemByTitle(title);
        if (item != null) {
            commandManager.executeCommand(new DeleteItineraryItemCommand(item, itemManager));
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
