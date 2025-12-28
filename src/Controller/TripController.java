package Controller;

import Model.*;
import Manager.TripManager;
import Command.*;

import java.util.List;

public class TripController {
    private TripManager tripManager;
    private CommandManager commandManager;

    // Retrieve Singleton instance
    public TripController() {
        this.tripManager = TripManager.getInstance();
        this.commandManager = new CommandManager();
    }

    // CREATE a trip via TripBuilder
    public Trip createTrip(String title, String destination, String description, String stringStartDate, String stringEndDate) {
        Trip trip = new TripBuilder()
                .setTitle(title)
                .setDestination(destination)
                .setDescription(description)
                .setStringStartDate(stringStartDate)
                .setStringEndDate(stringEndDate)
                .build();
        commandManager.executeCommand(new AddTripCommand(trip, tripManager));
        return trip;
    }


    public List<Trip> getAllTrips() {
        return tripManager.getAllTrips();
    }


    // DELETE a trip
    public boolean deleteTrip(String title) {
        Trip trip = tripManager.getTripByTitle(title);
        if (trip != null) {
            commandManager.executeCommand(new DeleteTripCommand(trip, tripManager));
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

    public CommandManager getCommandManager() {
        return commandManager;
    }


}
