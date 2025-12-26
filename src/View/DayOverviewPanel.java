package View;

import Manager.ItineraryItemManager;
import Model.ItineraryItem;
import Model.Trip;
import Observer.ItineraryItemsObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class DayOverviewPanel extends JPanel implements ItineraryItemsObserver {
    private Trip trip;
    private DefaultListModel<ItineraryItem> model = new DefaultListModel<>();
    // Centralized formatter to match the rest of the app
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private JTextField dayField;


    public DayOverviewPanel(Trip trip) {
        this.trip = trip;
        setLayout(new BorderLayout());

        // Register panel to listen for global itinerary changes
        ItineraryItemManager.getInstance().registerObserver(this);

        dayField = new JTextField(trip.getStringStartDate().format(FORMATTER), 10);
        JButton view = new JButton("View Day");

        JList<ItineraryItem> list = new JList<>(model);

        view.addActionListener(e -> refreshList());

        // Double click on an item to display its details
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    ItineraryItem item = list.getSelectedValue();
                    if (item != null) {
                        JOptionPane.showMessageDialog(
                                DayOverviewPanel.this,
                                item.getDetails(),
                                "Activity details",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });

        JPanel top = new JPanel();
        top.add(new JLabel("Date (dd-MM-yyyy):"));
        top.add(dayField);
        top.add(view);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    private void refreshList() {
        try{
            LocalDate selectedDay = LocalDate.parse(dayField.getText(), FORMATTER);

            if (selectedDay.isBefore(trip.getStringStartDate()) ||
                    selectedDay.isAfter(trip.getStringEndDate())) {

                JOptionPane.showMessageDialog(
                        this,
                        "Date must be between " +
                                trip.getStringStartDate().format(FORMATTER) +
                                " and " +
                                trip.getStringEndDate().format(FORMATTER),
                        "Invalid date",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            model.clear();
            for (ItineraryItem item : trip.getItineraryItems()){
                LocalDate itemDate = LocalDate.parse(item.getStringStartTime().substring(0, 10), FORMATTER);
                if (itemDate.equals(selectedDay)){
                    model.addElement(item);
                }
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date format. Use DD-MM-YYYY.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void update(List<ItineraryItem> items) {
        // Automatically called by the Manager when an item is added/deleted
        refreshList();
    }
}
