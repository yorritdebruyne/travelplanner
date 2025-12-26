package Model;

import java.util.List;

public class DefaultPriceStrategy implements PriceStrategy{

    @Override
    public double calculate(List<ItineraryItem> items) {
        return items.stream().mapToDouble(ItineraryItem::getPrice).sum();
    }
}
