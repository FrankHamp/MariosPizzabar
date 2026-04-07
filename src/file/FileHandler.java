package file;

import model.Pizza;
import service.Order;
import util.ExceptionHandler;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {


    // Konstanter til filnavne så de er defineret et sted
    // Læg mærke til at det er relative paths, og ikke absolutte paths
    // (Fordel når vi arbejder på forskellige maskiner)
    private static final String ORDER_FILE = "orders.csv";
    private static final String MENU_FILE = "menu.txt";


    // Gemmer en komplet ordre til vores CSV fil "orders.csv"
    public static void saveOrderToFile(Order order) {
        try {

            // Åbner filen og benytter os af append = true, så vi ikke overskriver ordre der allerede står i filen.
            FileWriter fileWriter = new FileWriter(ORDER_FILE, true);


            // Wrapper FileWriter i BufferedWriter for mere effektiv skrivning
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            // Skriver ordren som en CSV linje, ved brug af toCSV() fra Ordre klassen.
            bufferedWriter.write(order.toCSV());


            // Fortæller writeren at vi skal skrive på en ny linje,
            // så hver ordre får sin egen linje og vi ikke kun får en lang linjes tekst
            bufferedWriter.newLine();


            // Lukker writeren, så dataen bliver gemt.
            bufferedWriter.close();
        } catch (IOException e) {


            // Kalder ExceptionHandler hvis filen ikke kunne skrives til (Ikke findes)
            ExceptionHandler.handleFileNotFound(ORDER_FILE);
        }
    }


    // Læser menuen fra menu.txt og returnere en liste af Pizza objekter
    public ArrayList<Pizza> loadMenu() {

        // Opretter en tom ArrayListe til at gemme pizzaerne i
        ArrayList<Pizza> menuItems = new ArrayList<>();

        try {

            // Åbner menu.txt for at læse den
            Scanner scanner = new Scanner(new File(MENU_FILE));


            // Læser filen linje for linje
            while (scanner.hasNextLine()) {

                // Læser næste linje
                String line = scanner.nextLine();

                // Skipper tomme linjer
                if (line.isBlank()) continue;


                // Splitter linjerne med semicolon ind i et array af strings
                String[] parts = line.split(";");


                // Parser pizzanummeret fra det første element, og trimmer det (Fjerner alle unødvendige mellemrum)
                int number = Integer.parseInt(parts[0].trim());


                // Parser navnet fra det andet element, og trimmer det (Fjerner alle unødvendige mellemrum)
                String name = parts[1].trim();


                // Parser beskrivelsen fra det 3. element, og trimmer det (Fjerner alle unødvendige mellemrum)
                String description = parts[2].trim();


                // Parser prisen fra det 4 element, og trimmer det (Fjerner alle unødvendige mellemrum)
                double price = Double.parseDouble(parts[3].trim());


                // Laver et nyt Pizza object og tilføjer det til listen.
                menuItems.add(new Pizza(number, name, description, price));
            }

            // Lukker Scanneren efter at den har læst.
            scanner.close();
        } catch (FileNotFoundException e) {

            // Kalder ExceptionHandler hvis menu.txt ikke kan findes.
            ExceptionHandler.handleFileNotFound(MENU_FILE);
        }

        // Returnere listen af pizzaer, også selvom den er tom.
        return menuItems;
    }


    // Indlæser alle gemte ordrer fra vores CSV fil som strings
    public static ArrayList<String> loadStats() {


        // Laver en tom ArrayListe til midlertidigt at gemme ordre dataen
        ArrayList<String> stats = new ArrayList<>();

        try {

            // Åbner orders.csv for at læse den
            Scanner scanner = new Scanner(new File(ORDER_FILE));

            //Læser filen linje for linje
            while (scanner.hasNextLine()) {
                // Læser næste linje
                String line = scanner.nextLine();
                // Skipper tomme linjer
                if (!line.isBlank()) {
                    // Tilføjer linje til stats listen (Listen af ordre).
                    stats.add(line);
                }
            }

            // Vi husker at lukke scanneren efter vi har læst
            scanner.close();
        } catch (FileNotFoundException e) {

            // Kalder ExceptionHandler hvis ordre.csv ikke kan findes
            ExceptionHandler.handleFileNotFound(ORDER_FILE);
        }

        // Returnere listen af ordre
        return stats;
    }
}