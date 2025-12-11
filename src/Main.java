
import Controller.TripController;
import Manager.TripManager;
import View.TripListView;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

public class Main {



    public static void main(String[] args) {

        // Create TripListView singleton instance
        TripListView tripListView = new TripListView();

        // Register TripListView as observer of TripManager singleton instance
        TripManager.getInstance().registerObserver(tripListView);

        // Create TripManager singleton instance
        TripController tripController = new TripController();

        // Testing adding trip to Controller
        tripController.createTrip(
                "TripWithTheBoys",
                "Barcelona",
                "Weekend getaway",
                "01/05/2026",
                "05/05/2026"
        );

        // Add another trip
        tripController.createTrip(
                "FamilyHoliday",
                "Rome",
                "Summer trip",
                "10/07/2026",
                "20/07/2026"
        );

        // Update a trip
        tripController.updateTrip(
                "FamilyHoliday",
                "FamilyHoliday",
                "Paris",
                "Changed destination",
                "10/07/2026",
                "20/07/2026"
        );

        // Delete a trip
        tripController.deleteTrip("TripWithTheBoys");

        // Undo (herstel delete)
        tripController.undoCommand();

        // Redo (herstel undo)
        tripController.redoCommand();
    }
}