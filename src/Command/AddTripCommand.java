package Command;

import Model.Trip;
import Manager.TripManager;

// Concrete Command for adding a trip
public class AddTripCommand implements Command {
    private Trip trip;
    private TripManager tripManager;

    public AddTripCommand(Trip trip, TripManager tripManager) {
        this.trip = trip;
        this.tripManager = tripManager;
    }

    @Override
    public void execute() {
        tripManager.addTrip(trip);
    }

    @Override
    public void undo() {
        tripManager.removeTrip(trip);
    }
}
