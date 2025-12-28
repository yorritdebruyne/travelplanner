package Controller;

import Command.AddTravellerCommand;
import Command.CommandManager;
import Command.DeleteTravellerCommand;
import Command.UpdateTravellerCommand;
import Manager.TravellerManager;
import Model.*;

import java.util.List;

public class TravellerController {
    private TravellerManager travellerManager;
    private CommandManager commandManager;

    // Retrieve Singleton instance
    public TravellerController(CommandManager commandManager) {
        this.travellerManager = TravellerManager.getInstance();
        this.commandManager = commandManager;
    }

    // CREATE a traveller using the TravellerFactory
    public Traveller createTraveller(TravellerType type, String name, String mail, String phone,
                                     String nationality, String passportNumber, int age){
        Traveller traveller = TravellerFactory.createTraveller(type, name, mail, phone, nationality, passportNumber, age);
        commandManager.executeCommand(new AddTravellerCommand(traveller, travellerManager));
        return traveller;
    }


    public List<Traveller> getAllTravellers(){
        return travellerManager.getAllTravellers();
    }

}
