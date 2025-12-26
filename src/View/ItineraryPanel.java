package View;

import Controller.ItineraryItemController;
import Manager.ItineraryItemManager;
import Model.*;
import Observer.ItineraryItemsObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ItineraryPanel extends JPanel implements ItineraryItemsObserver {
    private Trip trip;
    private ItineraryItemController itineraryItemController;
    private DefaultListModel<ItineraryItem> model = new DefaultListModel<>();
    private JLabel totalPriceLabel = new JLabel();

    public ItineraryPanel(Trip trip, ItineraryItemController itineraryItemController){
        this.trip = trip;
        this.itineraryItemController = itineraryItemController;
        setLayout(new BorderLayout());

        // Register panel to listen for global itinerary changes
        ItineraryItemManager.getInstance().registerObserver(this);

        JList<ItineraryItem> list = new JList<>(model);

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    ItineraryItem item = list.getSelectedValue();
                    if (item != null) {
                        JOptionPane.showMessageDialog(
                                ItineraryPanel.this,
                                item.getDetails(),
                                "Activity details",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });


        JButton add = new JButton("Add Activity");
        JButton remove = new JButton("Remove Activity");

        add.addActionListener(e->{
            ItineraryItem item = ItineraryItemInputDialog.show(
                    (JFrame) SwingUtilities.getWindowAncestor(this)
            );
            if (item != null){
//                trip.addItineraryItem(item);
                itineraryItemController.createItem(trip, item);
            }
        });

        remove.addActionListener(e->{
            ItineraryItem selected = list.getSelectedValue(); // JList local variable
//            ItineraryItem selected = itemList.getSelectedValue();
            if(selected != null){
                itineraryItemController.deleteItem(trip, selected);

//                ItineraryItemController.deleteItem(trip, selected.getTitle());

//                ItineraryItemController.deleteItem(trip, String.valueOf(selected));
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);

        add(totalPriceLabel, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        refresh();
    }

    private void refresh(){
        model.clear();
        for ( ItineraryItem item : trip.getItineraryItems()){
            model.addElement(item);
        }
        totalPriceLabel.setText("Total price: €" + trip.getTotalPrice());
    }

    private void refreshRemove(){
        model.clear();
        for(ItineraryItem item : trip.getItineraryItems()){
            model.removeElement(item);
        }
        totalPriceLabel.setText("Total price: €" + trip.getTotalPrice());
    }

    @Override
    public void update(List<ItineraryItem> items) {
        refresh();
    }
}
