package View;

import Controller.TravellerController;
import Model.*;
import Observer.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TravellerPanel extends JPanel implements TravellerObserver{
    private Trip trip;
    private TravellerController travellerController;
    private DefaultListModel<Traveller> model = new DefaultListModel<>();

    public TravellerPanel(Trip trip){
        this.trip = trip;
        this.travellerController = new TravellerController();
        // Register panel to listen for global traveller changes
        Manager.TravellerManager.getInstance().registerObserver(this);

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


        JButton addExisting = new JButton("Add Existing");
        JButton createNew = new JButton("Create New");
        JButton remove = new JButton("Remove from Trip");

        // Add a traveller that already exists in the system
        addExisting.addActionListener(e -> {
            List<Traveller> all = travellerController.getAllTravellers();
            Traveller selection = (Traveller) JOptionPane.showInputDialog(
                    this, "Select Traveller", "Add to Trip",
                    JOptionPane.QUESTION_MESSAGE, null, all.toArray(), null);

            if (selection != null && !trip.getTravellers().contains(selection)) {
                trip.addTraveller(selection);
                refresh();
            }
        });


        // Create a brand new traveller via Controller (adds to global list and trip)
        createNew.addActionListener(e -> {
            Traveller t = TravellerInputDialog.show((JFrame) SwingUtilities.getWindowAncestor(this));
            if (t != null) {
                // Factory already generated the ID inside t,
                // Controller registers it globally, and we link it to the trip locally
                travellerController.createTraveller(
                        t instanceof AdminTraveller ? TravellerType.ADMIN : TravellerType.GUEST,
                        t.getName(), t.getMail(), t.getPhone(),
                        t.getNationality(), t.getPassportNumber(), t.getAge()
                );
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
        buttons.add(addExisting);
        buttons.add(createNew);
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

    @Override
    public void update(List<Traveller> travellers) {
        // If a traveller's details change globally, refresh the trip list
        refresh();
    }
}
