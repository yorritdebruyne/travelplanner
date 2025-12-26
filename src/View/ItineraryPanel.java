package View;

import Controller.ItineraryItemController;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItineraryPanel extends JPanel {
    private Trip trip;
    private ItineraryItemController itineraryItemController;
    private DefaultListModel<ItineraryItem> model = new DefaultListModel<>();
    private JLabel totalPriceLabel = new JLabel();
    private JList<ItineraryItem> itemList = new JList<>(model);

    public ItineraryPanel(Trip trip){
        this.trip = trip;
        setLayout(new BorderLayout());

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
                trip.addItineraryItem(item);
                refresh();
            }
        });

        remove.addActionListener(e->{
            ItineraryItem selected = list.getSelectedValue(); // JList local variable
//            ItineraryItem selected = itemList.getSelectedValue();
            if(selected != null){
                ItineraryItemController.deleteItem(trip, selected.getTitle());
//                ItineraryItemController.deleteItem(trip, String.valueOf(selected));
                refresh();
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
        totalPriceLabel.setText("Total price: $" + trip.getTotalPrice());
    }

    private void refreshRemove(){
        model.clear();
        for(ItineraryItem item : trip.getItineraryItems()){
            model.removeElement(item);
        }
        totalPriceLabel.setText("Total price: $" + trip.getTotalPrice());
    }
}
