package ui;

import model.*;
import service.Order;
import service.OrderHandler;
import util.ExceptionHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class PizzaBarUI {

    private ArrayList<Pizza> menuItems = new ArrayList<>();

    private OrderHandler orderHandler = new OrderHandler();
    private Scanner scanner;

    public void start() {

        loadMenu();

        boolean running = true;

        while (running) {

            System.out.println("\n=== Marios Pizzabar ===");
            System.out.println("1. Show menu");
            System.out.println("2. Add order");
            System.out.println("3. Show orders");
            System.out.println("4. Complete order");
            System.out.println("5. Remove order");
            System.out.println("6. Show specific order");
            System.out.println("7. Clear Order");
            System.out.println("8. Exit");

            try {

                int choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        showMenu();
                        break;

                    case 2:
                        addOrder();
                        break;

                    case 3:
                        showOrders();
                        break;

                    case 4:
                        completeOrder();
                        break;

                    case 5:
                        removeOrder();
                        break;

                    case 6:
                        showOrder();
                        break;

                    case 7:
                        clearOrders();
                        break;

                    case 8:
                        exitProgram();
                        running = false;
                        break;
                }

            } catch (InputMismatchException e ) {
                ExceptionHandler.handleInputMismatch(e);
                scanner.nextLine();
            }
        }
    }
// er loadMenu ikke overflødig, når der er noget lignende i selectPizzas?
    private void loadMenu() {

        // Hardkodet menu da Mario ikke har en fil endnu

        menuItems.add(new Pizza(1, "Margherita", "Klassisk tomat og mozzarella", 89.0));

        menuItems.add(new Pizza(2, "Pepperoni", "Krydret med pepperoni", 99.0));

        menuItems.add(new Pizza(3, "Quattro Formaggi", "Fire oste", 109.0));

        menuItems.add(new Pizza(4, "Hawaii", "Skinke og ananas", 95.0));

    }

    public void showMenu() {

        System.out.println("\n=== Menu ===");

        for (Pizza pizza : menuItems) {

            System.out.println(pizza.toString());

        }

    }

    public void addOrder() {

        // Kundenavn

        System.out.println("\nEnter customer name:");

        String customerName = scanner.nextLine();

        // Kundetype

        Customer customer = selectCustomerType(customerName);

        // Vælg pizzaer

        Pizza[] pizzaOrders = selectPizzas();

        // Afhentingstidspunkt

        LocalDateTime pickupTime = selectPickupTime();

        // Opret ordre via OrderHandler

        orderHandler.addOrder(pizzaOrders, customer, pickupTime);

        System.out.println("Order added successfully!");

    }

    private Customer selectCustomerType(String customerName) {

        System.out.println("\nSelect customer type:");

        System.out.println("1. Normal Customer (no discount)");

        System.out.println("2. VIP Customer (10% discount)");

        System.out.println("3. Employee Customer (20% discount)");


        try {

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    return new VIPCustomer(customerName);

                case 2:
                    return new EmployeeCustomer(customerName);

                case 3:
                    return new NormalCustomer(customerName);
            }
        } catch (InputMismatchException e) {
            ExceptionHandler.handleInputMismatch(e);

        }
        return null;
    }

    private Pizza[] selectPizzas() {
        int count = 0;

        try {
            FileReader reader = new FileReader("menu");
            BufferedReader bufferedReader = new BufferedReader(new FileReader("menu"));

            String line = bufferedReader.readLine();
            while (line != null) {
                count++;
                line = bufferedReader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            ExceptionHandler.handleIO(e);
        }


     //
        Pizza[] pizzaOrders = new Pizza[];


        showMenu();

        System.out.println("\nEnter pizza number (0 to finish):");

        while (count < pizzaOrders.length) {

            int pizzaNumber = scanner.nextInt();

            if (pizzaNumber == 0) break;

            Pizza selected = findPizza(pizzaNumber);

            if (selected != null) {

                pizzaOrders[count] = selected;

                count++;

                System.out.println("Added: " + selected.getName() + " - " + selected.getPrice() + "kr");

            } else {

                ExceptionHandler.handleInputMismatch(String.valueOf(pizzaNumber));

            }

        }

        return pizzaOrders;

    }

    private Pizza findPizza(int pizzaNumber) {

        for (Pizza pizza : menuItems) {

            if (pizza.getNumber() == pizzaNumber) { // bruger getNumber() fra Pizza klassen

                return pizza;

            }

        }

        return null;

    }

    private LocalDateTime selectPickupTime() {

        System.out.println("Enter pickup hour (HH):");

        int hour = scanner.nextInt();

        System.out.println("Enter pickup minute (MM):");

        int minute = scanner.nextInt();

        return LocalDateTime.of(

                LocalDateTime.now().toLocalDate(),

                LocalTime.of(hour, minute)
        );

    }

    public void showOrders() {

        orderHandler.sortOrdersByTime();

        ArrayList<Order> activeOrders = orderHandler.getActiveOrders();

        if (activeOrders.isEmpty()) {

            System.out.println("\nNo active orders.");

            return;

        }

        System.out.println("\n=== Active Orders ===");

        for (Order order : activeOrders) {

            System.out.println(order.toString());

        }

    }

    public void removeOrder() {

        System.out.println("Enter order ID to remove:");

        int orderID = scanner.nextInt();

        orderHandler.removeOrder(orderID);

        System.out.println("Order #" + orderID + " removed.");

    }

    public void completeOrder() {

        System.out.println("Enter order ID to complete:");

        int orderID = scanner.nextInt();

        orderHandler.completeOrder(orderID);

        System.out.println("Order #" + orderID + " completed and saved.");

    }

    public void showOrder() {

        System.out.println("Enter order ID to show:");

        int orderID = scanner.nextInt();

        for (Order order : orderHandler.getActiveOrders()) {

            if (order.getOrderID() == orderID) {

                System.out.println(order);

                return;

            }

        }

        ExceptionHandler.handleOrderNotFound(orderID);

    }

    public void clearOrders() {

        orderHandler.getActiveOrders().clear();

        System.out.println("All orders cleared.");

    }

    public void exitProgram() {

        System.out.println("Goodbye!");

    }

}
