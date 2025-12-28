package Model;

import Controller.TripController;
import Manager.TripManager;
import Model.Items.ActivityItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * INTEGRATION TESTS FOR TRIP: Verifies the interaction between Controller, Commands and Managers
 */
public class TripIT {
    private TripController tripController;
    private TripManager tripManager;

    @BeforeEach
    void setUp() {
        // We use the real controller and manager to test their integration
        tripController = new TripController();
        tripManager = TripManager.getInstance();

        // Cleanup: Since TripManager is a Singleton, we must clear it before each test
        for (Trip t : tripManager.getAllTrips()) {
            tripManager.removeTrip(t);
        }
    }

    @Test
    void testCreateTripAndUndoRedoIntegration() {
        String tripTitle = "Test Trip";

        // Action: create a trip via controller
        // This triggers: Controller -> Builder -> CommandManager -> AddTripCommand -> TripManager
        tripController.createTrip(tripTitle, "Test Destination", "Test Description", "28-12-2025", "01-01-2026");

        // Integration Check: Verify the trip was added to the manager
        Trip trip = tripManager.getTripByTitle(tripTitle);
        Assertions.assertNotNull(trip, "Trip was added to the manager");
        Assertions.assertEquals(tripTitle, trip.getTitle());

        // Integration Check: Verify Undo functionality
        // Controller -> CommandManager -> AddTripCommand.undo() -> TripManager.removeTrip()
        tripController.undoCommand();
        Assertions.assertNull(tripManager.getTripByTitle(tripTitle), "Trip was removed from the manager");
        // Integration Check: Verify Redo functionality
        // Controller -> CommandManager -> AddTripCommand.undo() -> TripManager.addTrip()
        tripController.redoCommand();
        Assertions.assertNotNull(tripManager.getTripByTitle(tripTitle), "Trip was added back to the manager");
    }

    @Test
    void testUpdateTripIntegration() {
        String oldTripTitle = "Test Trip";
        String newTripTitle = "Updated Trip";

        // Create a trip via controller
        tripController.createTrip(oldTripTitle, "Test Destination1", "Test Description1", "28-12-2025", "01-01-2026");
        // Perform an update via controller
        boolean updated = tripController.updateTrip(oldTripTitle, newTripTitle, "Test Destination2", "Test Description2", "29-12-2025", "02-01-2026");
        // Verify the trip was updated in the manager
        Assertions.assertTrue(updated, "Trip was updated in the manager");
        Assertions.assertNull(tripManager.getTripByTitle(oldTripTitle), "Old trip was removed from the manager");
        Trip updatedTrip = tripManager.getTripByTitle(newTripTitle);
        Assertions.assertNotNull(updatedTrip, "Trip was updated in the manager");
        Assertions.assertEquals(newTripTitle, updatedTrip.getTitle());
    }
}