package Model.Items;

import Model.*;

public class TransportItem extends ItineraryItem {
    public TransportItem(String title, String stringStartTime, String stringEndTime, String location, String description, double price) {
        super(title, stringStartTime, stringEndTime, location, description, price);
    }

    @Override
    public String getType() {
        return "Transport";
    }

}
