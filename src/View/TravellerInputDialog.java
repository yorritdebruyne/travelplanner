package View;

import Model.*;
import javax.swing.*;
import java.util.UUID;

public class TravellerInputDialog {

    public static Traveller show(JFrame parent){
        JTextField name = new JTextField();
        JTextField mail = new JTextField();
        JTextField phone = new JTextField();
        JTextField nationality = new JTextField();
        JTextField passportNumber = new JTextField();
        JTextField age = new JTextField();
        JComboBox<TravellerType> typeCombo = new JComboBox<>(TravellerType.values());

        Object[]  fields = {
                "Type", typeCombo,
                "name",name,
                "mail",mail,
                "phone",phone,
                "nationality",nationality,
                "passportNumber",passportNumber,
                "age",age
        };

        int result = JOptionPane.showConfirmDialog(parent, fields, "Add Traveller", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            return TravellerFactory.createTraveller(
                    TravellerType.GUEST, // Or selected type from a dropdown
                    name.getText(),
                    mail.getText(),
                    phone.getText(),
                    nationality.getText(),
                    passportNumber.getText(),
                    Integer.parseInt(age.getText())
            );
        }
        return null;
    }

}
