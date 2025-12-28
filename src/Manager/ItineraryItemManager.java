package Manager;
import Model.*;
import Observer.*;
import java.util.ArrayList;
import java.util.List;

public class ItineraryItemManager {
    private static volatile ItineraryItemManager instance;
    private List<ItineraryItem> items;
    private List<ItineraryItemsObserver> observers;

    private ItineraryItemManager() {
        items = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ItineraryItemManager getInstance() {
        if (instance == null) {
            synchronized (ItineraryItemManager.class) {
                if (instance == null) {
                    instance = new ItineraryItemManager();
                }
            }
        }
        return instance;
    }

    public void registerObserver(ItineraryItemsObserver observer){
        observers.add(observer);
    }

    public void removeObserver(ItineraryItemsObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (ItineraryItemsObserver observer : observers) {
            observer.update(getAllItems());
        }
    }

    public void addItem(ItineraryItem item){
        items.add(item);
        notifyObservers();
    }

    public void removeItem(ItineraryItem item){
        items.remove(item);
        notifyObservers();
    }

    public List<ItineraryItem> getAllItems(){
        return new ArrayList<>(items);
    }

    public ItineraryItem getItemByTitle(String title) {
        for (ItineraryItem item : items) {
            if (item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }



}
