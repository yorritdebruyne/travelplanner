package View;

import Model.*;
import javax.swing.*;

public class ItineraryItemInputDialog {

    public static ItineraryItem show(JFrame parent){
        JTextField title = new JTextField();
        JTextField date = new JTextField();
        JTextField startTime = new JTextField();
        JTextField endTime = new JTextField();
        JTextField type = new JTextField();
        JTextField location = new JTextField();
        JTextField description = new JTextField();
        JTextField price = new JTextField();
        JComboBox<ItineraryType> typeCombo = new JComboBox<>(ItineraryType.values());

        Object[] fields = {
                "Title", title,
                "Date (DD-MM-YYYY)", date,
                "Start Time HH:mm", startTime,
                "End Time HH:mm", endTime,
                "Type", typeCombo,
                "Location", location,
                "Description", description,
                "Price", price
        };

        int result = JOptionPane.showConfirmDialog(parent, fields, "Add Itinerary Item", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            return new ItemBuilder()
                    .setTitle(title.getText())
                    .setStringStartTime(date.getText() + " " + startTime.getText())
                    .setStringEndTime(date.getText() + " " + endTime.getText())
                    .setType(type.getText())
                    .setLocation(location.getText())
                    .setDescription(description.getText())
                    .setTotalPrice(Double.parseDouble(price.getText()))
                    .build();
        }
        return null;
    }
}
