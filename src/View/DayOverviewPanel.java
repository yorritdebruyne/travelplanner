package View;

import Model.ItineraryItem;
import Model.Trip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DayOverviewPanel extends JPanel {

    private DefaultListModel<ItineraryItem> model = new DefaultListModel<>();
    // Centralized formatter to match the rest of the app
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public DayOverviewPanel(Trip trip) {
        setLayout(new BorderLayout());

        JTextField dayField = new JTextField(trip.getStringStartDate().format(FORMATTER));
        JButton view = new JButton("View Day");

        JList<ItineraryItem> list = new JList<>(model);

        view.addActionListener(e -> {
            try {
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
                for (ItineraryItem item : trip.getItineraryItems()) {
                    // Extract the date part (first 10 chars: DD-MM-YYYY) and parse it
                    String itemDateString = item.getStringStartTime().substring(0, 10);
                    LocalDate itemDate = LocalDate.parse(itemDateString, FORMATTER);

                    if (itemDate.equals(selectedDay)) {
                        model.addElement(item);
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid date format. Use YYYY-MM-DD.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

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
}
