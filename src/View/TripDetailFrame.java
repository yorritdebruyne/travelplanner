package View;

import Controller.ItineraryItemController;
import Command.CommandManager;
import Model.Trip;
import javax.swing.*;

public class TripDetailFrame extends JFrame {

    public TripDetailFrame(Trip trip, CommandManager commandManager){
        setTitle("Trip: " + trip.getTitle());
        setSize(700, 500);

        ItineraryItemController itemController = new ItineraryItemController(commandManager);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Itinerary", new ItineraryPanel(trip, itemController));
        tabs.add("Travellers",new TravellerPanel(trip, commandManager));
        tabs.add("Day Overview", new DayOverviewPanel(trip));

        add(tabs);
    }
}
