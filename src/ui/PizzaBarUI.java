package ui;
import model.*;
import file.*;
import java.util.Scanner;
import service.*;

import java.util.ArrayList;

/* + void start()
- Greeting
+ void ShowInterface
+ void addOrder()
printer hele pizza-menuen

--------------------------------
+ void removeOrder()
+ void showOrder()
+ void clearOrder()
--------------------------------

+ void exitProgram() */

public class PizzaBarUI {

    private ArrayList<Pizza> menuItems;
    private Scanner scanner = new Scanner(System.in);
    private FileHandler fileHandler = new FileHandler();



    public void start() {
        //System.out.println();
        boolean running = true;

        while (running) {

        showInterface();
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 = addOrder(); break;
            case 2 = removeOrder(); break;
            case 3 = showOrder(); break;
            case 4 = clearOrder(); break;
            case 5 = exitProgram(); break;
            }
    }

    public void showInterface() {
            System.out.println("\n1. Tilføj ordre");
            System.out.println("Fjern ordre(r)");
            System.out.println("vis ordre(r)");
            System.out.println("clear ordre");
        }





    }

}
