package View;

import Controller.TripController;
import Model.Trip;

import javax.swing.*;

public class TripInputDialog {

    public static Trip show(JFrame parent, TripController controller) {
        JTextField title = new JTextField();
        JTextField destination = new JTextField();
        JTextField description = new JTextField();
        JTextField startDate = new JTextField();
        JTextField endDate = new JTextField();

        Object[] fields = {
                "Title", title,
                "Destination", destination,
                "Description", description,
                "Start Date (DD-MM-YYYY)", startDate,
                "End Date (DD-MM-YYYY)", endDate
        };

        int result = JOptionPane.showConfirmDialog(parent, fields, "Create Trip", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION) {
            try{
                return controller.createTrip(
                        title.getText(),
                        destination.getText(),
                        description.getText(),
                        startDate.getText(),
                        endDate.getText()
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parent, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }
}
