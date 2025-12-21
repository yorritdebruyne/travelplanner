package Command;

import Manager.TravellerManager;
import Model.Traveller;

public class UpdateTravellerCommand implements Command{
    private TravellerManager travellerManager;
    private Traveller oldTraveller;
    private Traveller newTraveller;

    public UpdateTravellerCommand(TravellerManager travellerManager, Traveller oldTraveller, Traveller newTraveller) {
        this.travellerManager = travellerManager;
        this.oldTraveller = oldTraveller;
        this.newTraveller = newTraveller;
    }

    @Override
    public void execute() {
        travellerManager.removeTraveller(oldTraveller.getId());
        travellerManager.addTraveller(newTraveller);
    }

    @Override
    public void undo() {
        travellerManager.removeTraveller(newTraveller.getId());
        travellerManager.addTraveller(oldTraveller);
    }
}
