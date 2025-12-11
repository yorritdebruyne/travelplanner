package Command;


import Manager.TripManager;
import Model.Trip;

// test
public class UpdateTripCommand implements Command{
    private TripManager tripManager;
    private Trip oldTrip;
    private Trip newTrip;

    public UpdateTripCommand(TripManager tripManager, Trip oldTrip, Trip newTrip) {
        this.tripManager = tripManager;
        this.oldTrip = oldTrip;
        this.newTrip = newTrip;
    }

    @Override
    public void execute() {
        tripManager.removeTrip(oldTrip);
        tripManager.addTrip(newTrip);
    }

    @Override
    public void undo() {
        tripManager.removeTrip(newTrip);
        tripManager.addTrip(oldTrip);
    }
}
