package service;

import file.FileHandler;
import model.*;
import util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderHandler {

    private ArrayList<Order> activeOrders = new ArrayList<>();
    private ArrayList<Order> completedOrders = new ArrayList<>();
    private int nextOrderID = 1;
    private Comparators comparator = new Comparators();

    public void addOrder(Pizza[] pizzaOrders, Customer customer, LocalDateTime pickupTime) {
        Order newOrder = new Order(nextOrderID++, pickupTime, pizzaOrders, customer, OrderStatus.ORDER_PLACED);
        activeOrders.add(newOrder);
    }

    public Order removeOrder(int orderID) {
        for (int i = 0; i < activeOrders.size(); i++) {
            if (activeOrders.get(i).getOrderID() == orderID) {
                return activeOrders.remove(i);
            }
        }
        ExceptionHandler.handleOrderNotFound(orderID);
        return null;
    }

    public void completeOrder(int orderID) {
        Order completedOrder = removeOrder(orderID);
        if (completedOrder != null) {
            completedOrder.setStatus(OrderStatus.ORDER_COMPLETED);
            completedOrders.add(completedOrder);
            FileHandler.saveOrderToFile(completedOrder);
        }
    }

    public void sortOrdersByTime() {
        activeOrders.sort(comparator);
    }

    // Getters
    public ArrayList<Order> getActiveOrders() { return activeOrders; }
    public ArrayList<Order> getCompletedOrders() { return completedOrders; }
}
