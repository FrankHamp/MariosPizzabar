package ui;
import model.*;
import file.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
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

- ArrayList<Pizza> menuItems
- ArrayList<Order> activeOrders
- Scanner scanner
- OrderFileHandler fileHandler

+ void exitProgram() */

public class PizzaBarUI {

package ui;
import model.*;
import file.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
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

- ArrayList<Pizza> menuItems
- ArrayList<Order> activeOrders
- Scanner scanner
- OrderFileHandler fileHandler

+ void exitProgram() */

    public class PizzaBarUI {

        private ArrayList<Pizza> menuItems = new ArrayList<>();
        //læser items fra marius' tekstfil og smider hver linje ind i et arraylist
        private ArrayList<Order[]> activeOrders = new ArrayList<>();
        private Scanner scanner;
        private OrderHandler fileHandler;



        public PizzaBarUI() {
            scanner = new Scanner(System.in);
            fileHandler = new OrderHandler();
        }

        public void start() {

            boolean running = true;

            while (running) {

                showInterface();

                try {

                    int choice = scanner.nextInt();

                    switch (choice) {

                        case 1:
                            addOrder();
                            break;

                        case 2:
                            removeOrder();
                            break;

                        case 3:
                            showOrder();
                            break;

                        case 4:
                            clearOrder();
                            break;

                        case 5:
                            exitProgram();
                            break;

                    }

                }
                catch() {
                    //lav errorhandler senere
                }
            }
        }

        public void showInterface() {

            System.out.println("\nVelkommen Alfonzo!");
            System.out.println("1. Tilføj ordre");
            System.out.println("2. Fjern ordre(r)");
            System.out.println("3. vis ordre(r)");
            System.out.println("4. ryd ordrer");
            System.out.println("5. Afbryd");
        }

        private void showMenu() {

            //skal muligvis laves om -------------------
            String filePath = "menukort.csv";
            String line;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                // midlertidig Error ---------------------
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        private void addOrder() {

            try {

                showMenu();

                System.out.print("Hvor mange pizzaer vil du købe?");
                int numberOfPizzas = scanner.nextInt();
                scanner.nextLine();



            /* System.out.println("Hvor mange pizzaer vil du bestille?");
            int antalPizzaer = scanner.nextInt();
            Pizza[] numberOfPizzas = menuItems.toArray(new Pizza[antalPizzaer]);
             //= menuItems.toArray(new Pizza[antalPizzaer]);

            /* for (int i = 0; i < numberOfCats; i++) {
            System.out.print("Enter name for cat " + (i + 1) + ": ");
            String name = scanner.nextLine();
            cats[i] = new Cat(name); */

                activeOrders.add(new Order[0]);

            } catch (Exception e) { //----------------------
                throw new RuntimeException(e);
            }

        }

        private void removeOrder() {


        }

        private void showOrder() {

        }

        private void clearOrder() {

        }

        private void exitProgram() {

        }



    }



}
