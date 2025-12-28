package Model;

public class ItemBuilder {
    String title, stringStartTime, stringEndTime, type, location, description;
    double totalPrice;

    public ItemBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemBuilder setStringStartTime(String stringStartTime) {
        this.stringStartTime = stringStartTime;
        return this;
    }

    public ItemBuilder setStringEndTime(String stringEndTime) {
        this.stringEndTime = stringEndTime;
        return this;
    }

    public ItemBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public ItemBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public ItemBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemBuilder setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public ItineraryItem build() {
        // Convert the String type to the ItineraryType enum
        ItineraryType itineraryType;
        try {
            itineraryType = ItineraryType.valueOf(type.toUpperCase());
        } catch (Exception e) {
            // Default to ACTIVITY if the type is invalid
            itineraryType = ItineraryType.ACTIVITY;
        }

        // Use the factory to create the specific subclass instance
        return ItineraryItemFactory.createItem(
                itineraryType,
                title,
                stringStartTime,
                stringEndTime,
                location,
                description,
                totalPrice
        );
    }

}
