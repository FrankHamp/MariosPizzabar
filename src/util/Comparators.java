package util;

import java.util.Comparator;
import service.*;

public class Comparators implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return o1.getPickupTime().compareTo(o2.getPickupTime());
    }
}
