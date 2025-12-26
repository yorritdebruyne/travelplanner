package Model.Items;

import Model.ItineraryItem;

public class ActivityItem extends ItineraryItem {
    public ActivityItem(String title, String stringStartTime, String stringEndTime, String location, String description, double price) {
        super(title, stringStartTime, stringEndTime, location, description, price);
    }

    @Override
    public String getType() {
        return "Activity";
    }
}
