package Model;

import java.util.List;

public interface PriceStrategy {
    double calculate(List<ItineraryItem> items);
}
