package Model.Items;

import Model.ItineraryItem;

public class AccommodationItem extends ItineraryItem {
    public AccommodationItem(String title, String stringStartTime, String stringEndTime, String location, String description, double price) {
        super(title, stringStartTime, stringEndTime, location, description, price);
    }

    @Override
    public String getType() {
        return "Accommodation";
    }
}
