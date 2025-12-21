package Command;

import Manager.TravellerManager;
import Model.Traveller;

public class AddTravellerCommand implements Command {
    private Traveller traveller;
    private TravellerManager travellerManager;

    public AddTravellerCommand(Traveller traveller, TravellerManager travellerManager) {
        this.traveller = traveller;
        this.travellerManager = travellerManager;
    }

    @Override
    public void execute() {
        travellerManager.addTraveller(traveller);
    }

    @Override
    public void undo() {
        travellerManager.removeTraveller(traveller.getId());
    }
}
