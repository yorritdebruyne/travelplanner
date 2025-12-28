package Model;

import Model.Items.ActivityItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

/**
 * UNIT TESTS: Focuses on the internal logic of Trip
 */
public class TripUnitTest {
    private Trip trip;

    @BeforeEach
    void setUp() {
        // Initialize trip before each test to ensure no side effects
        trip = new Trip("Test Trip", "Test Destination", "Test Description", "28-12-2025", "01-01-2026");
    }

    // Verifies if the fields are correctly initialized and dates are parsed
    @Test
    void testTripCreation() {
        Assertions.assertEquals("Test Trip", trip.getTitle());
        Assertions.assertEquals("Test Destination", trip.getDestination());
        Assertions.assertEquals(LocalDate.of(2025, 12, 28), trip.getStringStartDate());
        Assertions.assertEquals(LocalDate.of(2026, 1, 1), trip.getStringEndDate());
    }

    // Test adding an item within the trip dates
    @Test
    void testAddValidItineraryItem() {
        ActivityItem item = new ActivityItem("Test Activity", "29-12-2025 10:00", "30-12-2025 12:00", "Test Location", "Test Description", 25.0);
        trip.addItineraryItem(item);

        Assertions.assertEquals(1, trip.getItineraryItems().size(), "Item was added to the trip");
        Assertions.assertEquals(25.0, trip.getTotalPrice(), "Total price was updated correctly");
    }

    // Test adding an item with an invalid date range
    @Test
    void testAddInvalidItineraryItem1() {
        ActivityItem item = new ActivityItem("Test Activity", "29-12-2025 10:00", "28-12-2025 12:00", "Test Location", "Test Description", 25.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {trip.addItineraryItem(item);}, "Should throw exception when item is in an invalid date range.");
    }

    // Test adding an item outside the trip dates
    @Test
    void testAddInvalidItineraryItem2() {
        ActivityItem item = new ActivityItem("Test Activity", "31-01-2025 10:00", "01-02-2025 12:00", "Test Location", "Test Description", 25.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {trip.addItineraryItem(item);}, "Should throw exception when an item is outside the trip dates.");
    }

    // Test deleting an item
    @Test
    void testDeleteItineraryItem() {
        ActivityItem item = new ActivityItem("Test Activity", "29-12-2025 10:00", "30-12-2025 12:00", "Test Location", "Test Description", 25.0);
        trip.addItineraryItem(item);
        trip.removeItineraryItem(item);

        Assertions.assertEquals(0, trip.getItineraryItems().size(), "Item was deleted from the trip");
        Assertions.assertEquals(0.0, trip.getTotalPrice(), "Total price was reset correctly");
    }

    // Retrieves items scheduled for a given day
    @Test
    void testGetItemsForDay() {
        ActivityItem item1 = new ActivityItem("Test Activity 1", "29-12-2025 10:00", "29-12-2025 12:00", "Test Location 1", "Test Description 1", 25.0);
        ActivityItem item2 = new ActivityItem("Test Activity 2", "30-12-2025 13:00", "31-12-2025 14:00", "Test Location 2", "Test Description 2", 25.0);
        trip.addItineraryItem(item1);
        trip.addItineraryItem(item2);

        // Verify we only get items for the specific day requested
        List<ItineraryItem> itemsFor29th = trip.getItemsForDay(LocalDate.of(2025, 12, 29));
        Assertions.assertEquals(1, itemsFor29th.size());
        Assertions.assertEquals("Test Activity 1", itemsFor29th.get(0).getTitle());
    }
}
