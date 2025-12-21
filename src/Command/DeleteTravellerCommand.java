package Command;

import Manager.TravellerManager;
import Model.Traveller;

public class DeleteTravellerCommand implements Command{
    private Traveller traveller;
    private TravellerManager travellerManager;

    public DeleteTravellerCommand(Traveller traveller, TravellerManager travellerManager) {
        this.traveller = traveller;
        this.travellerManager = travellerManager;
    }

    @Override
    public void execute() {
        travellerManager.removeTraveller(traveller.getId());
    }

    @Override
    public void undo() {
        travellerManager.addTraveller(traveller);
    }
}

