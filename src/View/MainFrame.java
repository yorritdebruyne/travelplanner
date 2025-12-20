package View;

import Controller.TripController;
import Model.Trip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainFrame extends JFrame {
    private TripController tripController = new TripController();
    private DefaultListModel<Trip> tripModel = new DefaultListModel<>();
    private JList<Trip> tripList = new JList<>(tripModel);

    public MainFrame(){
        setTitle("Travel Planner");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tripList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Trip trip = tripList.getSelectedValue();
                    if (trip != null) {
                        JOptionPane.showMessageDialog(
                                MainFrame.this,
                                trip.getDetails(),
                                "Trip details",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });


        JButton addTrip = new JButton("Add Trip");
        JButton openTrip = new JButton("Open Trip");
        JButton removeTrip = new JButton("Remove Trip");

        addTrip.addActionListener(e -> {
            Trip trip = TripInputDialog.show(this, tripController);
            if(trip != null)refresh();
        });

        openTrip.addActionListener(e -> {
            Trip selected = tripList.getSelectedValue();;
            if(selected != null) {
                new TripDetailFrame(selected).setVisible(true);
            }
        });

        removeTrip.addActionListener(e -> {
            Trip selected = tripList.getSelectedValue();
            if(selected != null) {
                tripController.deleteTrip(selected.getTitle());
            }
        });

        add(new JScrollPane(tripList), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(addTrip);
        buttons.add(openTrip);
        buttons.add(removeTrip);
        add(buttons, BorderLayout.SOUTH);

        refresh();
    }

    private void refresh(){
        tripModel.clear();
        for(Trip t : tripController.getAllTrips()){
            tripModel.addElement(t);
        }
    }
}
