package View;

import Model.Trip;
import javax.swing.*;

public class TripDetailFrame extends JFrame {

    public TripDetailFrame(Trip trip){
        setTitle("Trip: " + trip.getTitle());
        setSize(700, 500);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Itinerary", new ItineraryPanel(trip));
        tabs.add("Travellers",new TravellerPanel(trip));
        tabs.add("Day Overview", new DayOverviewPanel(trip));

        add(tabs);
    }
}
