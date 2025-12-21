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
    public TravellerController() {
        this.travellerManager = TravellerManager.getInstance();
        this.commandManager = new CommandManager();
    }

    // CREATE a traveller using the TravellerFactory
    public Traveller createTraveller(TravellerType type, String name, String mail, String phone,
                                     String nationality, String passportNumber, int age){
        Traveller traveller = TravellerFactory.createTraveller(type, name, mail, phone, nationality, passportNumber, age);
        commandManager.executeCommand(new AddTravellerCommand(traveller, travellerManager));
        return traveller;
    }

    // READ
    public Traveller getTravellerById(String id){
        return travellerManager.getTravellerById(id);
    }

    public List<Traveller> getAllTravellers(){
        return travellerManager.getAllTravellers();
    }

    // UPDATE a traveller
    public boolean updateTraveller(String id, TravellerType newType, String name, String mail,
                                   String phone, String nationality, String passportNumber, int age){
        Traveller oldTraveller = travellerManager.getTravellerById(id);
        if(oldTraveller == null) return false;

        // newTraveller overwrites oldTraveller with the same id
        Traveller newTraveller = TravellerFactory.createTraveller(newType, id, name, mail, phone, nationality, passportNumber, age);
        commandManager.executeCommand(new UpdateTravellerCommand(travellerManager, oldTraveller, newTraveller));
        return true;
    }

    // DELETE
    public boolean deleteTraveller(String id){
        Traveller traveller = travellerManager.getTravellerById(id);
        if (traveller == null) return false;

        commandManager.executeCommand(new DeleteTravellerCommand(traveller, travellerManager));
        return true;
    }

    // Undo/Redo last command
    public void undoCommand() {commandManager.undoCommand();}
    public void redoCommand() {commandManager.redoCommand();}
}
