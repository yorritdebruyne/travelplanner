package Model;

import Model.Items.AccommodationItem;
import Model.Items.ActivityItem;
import Model.Items.TransportItem;

public class ItineraryItemFactory {
    public static ItineraryItem createItem(ItineraryType type, String title, String startTime, String endTime, String location, String description, double price){
        return switch (type){
            case ACCOMMODATION -> new AccommodationItem(title, startTime, endTime, location, description, price);
            case TRANSPORT -> new TransportItem(title, startTime, endTime, location, description, price);
            case ACTIVITY -> new ActivityItem(title, startTime, endTime, location, description, price);
        };
    }
}
