package View;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TravellerPanel extends JPanel {
    private Trip trip;
    private DefaultListModel<Traveller> model = new DefaultListModel<>();

    public TravellerPanel(Trip trip){
        this.trip = trip;
        setLayout(new BorderLayout());

        JList<Traveller> list = new JList<>(model);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Traveller traveller = list.getSelectedValue();
                    if (traveller != null) {
                        JOptionPane.showMessageDialog(
                                TravellerPanel.this,
                                traveller.getDetails(),
                                "Traveller details",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });


        JButton add = new JButton("Add Traveller");
        JButton remove = new JButton("Remove Traveller");

        add.addActionListener(e -> {
            Traveller t = TravellerInputDialog.show(
                    (JFrame) SwingUtilities.getWindowAncestor(this)
            );
            if (t != null) {

                trip.addTraveller(t);
                refresh();
            }
        });

        remove.addActionListener(e -> {
            Traveller selected = list.getSelectedValue();
            if(selected != null){
                trip.removeTraveller(selected);
                refresh();
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        refresh();
    }

    private void refresh(){
        model.clear();
        for(Traveller t : trip.getTravellers()){
            model.addElement(t);
        }
    }
}
