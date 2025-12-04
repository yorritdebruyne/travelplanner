package Command;

import Manager.TripManager;
import Model.Trip;

// Concrete Command for deleting a trip
public class DeleteTripCommand implements Command{
    private Trip trip;
    private TripManager tripManager;

    public DeleteTripCommand(Trip trip, TripManager tripManager) {
        this.trip = trip;
        this.tripManager = tripManager;
    }

    @Override
    public void execute() {
        tripManager.removeTrip(trip);
    }

    @Override
    public void undo() {
        tripManager.addTrip(trip);
    }
}
