package Model;

public class TripBuilder {
    private String title, destination, description, stringStartDate, stringEndDate;

    // Fluent setters (setters who give 'this' instead of 'void')
    public TripBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TripBuilder setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public TripBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TripBuilder setStringStartDate(String stringStartDate) {
        this.stringStartDate = stringStartDate;
        return this;
    }

    public TripBuilder setStringEndDate(String stringEndDate) {
        this.stringEndDate = stringEndDate;
        return this;
    }

    public Trip build() {
        return new Trip(title, destination, description, stringStartDate, stringEndDate);
    }
}
