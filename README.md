# Travelplanner
A robust, object-oriented Java desktop application designed for planning and managing travel itineraries. This project demonstrates software design principles.
## Key Features
- Trip Management: Create, update, and delete trips with specific titles, destinations, and date ranges.
- Itinerary Planning: Add various items to a trip, including Activities, Accommodations, and Transport. Each item includes timing, location, and cost details.
- Traveller Management: Maintain a list of travellers for each trip, supporting different roles such as Admin and Guest.
- Undo/Redo System: A full command history allows you to revert or repeat any action (adding trips, items, travellers, etc.).
- Cost Calculation Strategy: Automatically calculates the total cost of a trip. The system is designed to support different pricing strategies (e.g., discounts or currency conversions).
- Interactive GUI: Built with Java Swing, featuring a tabbed interface for detailed trip views and a calendar-style day overview.
- Data Consistency: Validates item dates against trip dates and ensures start dates are before end dates.
## Architecture & Design Patterns
1. Command Pattern:
  Implementation: Command interface, CommandManager, and concrete commands like AddTripCommand, DeleteTravellerCommand, etc.
  Purpose: Encapsulates every CRUD operation as an object, enabling a robust Undo/Redo functionality.
2. Singleton Pattern:
  Implementation: TripManager, ItineraryItemManager, and TravellerManager.
  Detail: Uses Double-checked locking with the volatile keyword to ensure thread-safe access to the managers that hold the application state.
3. Observer Pattern:
  Implementation: TripObserver, ItineraryItemsObserver, and TravellerObserver.
  Purpose: Views register as observers to Managers. When data changes (e.g., a trip is added), the Managers notify all registered views to refresh automatically.
4. Builder Pattern:
  Implementation: TripBuilder and ItemBuilder.
  Purpose: Provides a fluent API for constructing complex objects step-by-step, improving code readability.
5. Factory Pattern:
  Implementation: TravellerFactory and ItineraryItemFactory.
  Purpose: Centralizes the creation of different types of travellers (Admin/Guest) and itinerary items (Activity/Transport/Accommodation).
6. Strategy Pattern:
  Implementation: PriceStrategy interface and DefaultPriceStrategy.
  Purpose: Decouples the cost calculation logic from the Trip class, allowing for different pricing models to be injected at runtime.
## overview.txt
Gives the full overview of the core idea behind our project (before the actual coding)
## Testing
- Unit Tests (TripUnitTest): Validates the internal logic of the Trip class
- Integration Tests (TripIT)
## Detailed Implementation Notes
- Date Formatting in dd-MM-yyyy
- ID Generation: Travellers are assigned a unique UUID upon creation in the TravellerFactory to ensure they can be uniquely identified even if their names are updated.
- Thread Safety: Singleton Managers use double-checked locking to prevent race conditions during initialization.
- Validation: The Trip class performs strict validation to ensure that itinerary items are not scheduled outside the trip's duration.
