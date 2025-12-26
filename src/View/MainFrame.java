package View;

import Controller.TripController;
import Manager.TripManager;
import Model.Trip;
import Observer.TripObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class MainFrame extends JFrame implements TripObserver {
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


        TripManager.getInstance().registerObserver(this);

        JButton addTrip = new JButton("Add Trip");
        JButton openTrip = new JButton("Open Trip");
        JButton removeTrip = new JButton("Remove Trip");
        JButton undo = new JButton("Undo");
        JButton redo = new JButton("Redo");

        addTrip.addActionListener(e -> {
            Trip trip = TripInputDialog.show(this, tripController);
            if(trip != null)refresh();
        });

        openTrip.addActionListener(e -> {
            Trip selected = tripList.getSelectedValue();;
            if(selected != null) {
                new TripDetailFrame(selected, tripController.getCommandManager()).setVisible(true);
            }
        });

        removeTrip.addActionListener(e -> {
            Trip selected = tripList.getSelectedValue();
            if(selected != null) {
                tripController.deleteTrip(selected.getTitle());
                refresh();
            }
        });

        undo.addActionListener(e -> tripController.undoCommand());
        redo.addActionListener(e -> tripController.redoCommand());

        add(new JScrollPane(tripList), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(addTrip);
        buttons.add(openTrip);
        buttons.add(removeTrip);
        buttons.add(undo);
        buttons.add(redo);
        add(buttons, BorderLayout.SOUTH);

        update(tripController.getAllTrips());

//        refresh();
    }

    private void refresh(){
        tripModel.clear();
        for(Trip t : tripController.getAllTrips()){
            tripModel.addElement(t);
        }
    }

    @Override
    public void update(List<Trip> trips) {
        tripModel.clear();
        for(Trip trip : trips){
            tripModel.addElement(trip);
        }
    }
}
