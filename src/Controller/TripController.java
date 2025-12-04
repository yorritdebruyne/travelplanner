package Controller;

import Model.Trip;
import Model.TripBuilder;
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

    // READ all trips
    public Trip getTripByTitle(String title) {
        return tripManager.getTripByTitle(title);
    }

    public List<Trip> getAllTrips() {
        return tripManager.getAllTrips();
    }

    // UPDATE a trip via TripBuilder
    public boolean updateTrip(String oldTitle, String newTitle, String newDestination, String newDescription, String newStringStartDate, String newStringEndDate) {
        Trip oldTrip = tripManager.getTripByTitle(oldTitle);
        if (oldTrip != null) {
            Trip newTrip = new TripBuilder()
                    .setTitle(newTitle)
                    .setDestination(newDestination)
                    .setDescription(newDescription)
                    .setStringStartDate(newStringStartDate)
                    .setStringEndDate(newStringEndDate)
                    .build();

            commandManager.executeCommand(new UpdateTripCommand(tripManager, oldTrip, newTrip));
            return true;
        }
        return false;
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
}
