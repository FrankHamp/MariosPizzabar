package model;

// Skabelon for klassen
public class Pizza {
    // encapsulation. Det er vigtigt så prisen ikke kan ændres udefra (but y'all already kno' that)
    private int number;
    private String name;
    private String toppings;
    private double price;

    // Constructor. Den metode der kører når man skriver new pizza
    public Pizza(int number, String name, String toppings, double price) {
        this.number = number;
        this.name = name;
        this.toppings = toppings;
        this.price = price;
    }

    // Real go Getters, de tillader andre klasser at tilgå værdien fra private uden det kan ændres
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Overrider og laver en ny version af to string Method(Man)
    @Override
    // to string gør det lettere at læse så man ikke får hexadecimal
    public String toString() {
        return "#" + number + "" + name + ": " + toppings + "......" + price + " kr.";
    }
}
