package model;

// Skabelon for klassen
public class Pizza {
    // encapsulation. Det er vigtigt så prisen ikke kan ændres udefra (but y'all already kno' that)
    private int number;
    private String name;
    private String description;
    private double price;

    // Constructor. Den metode der kører når man skriver new pizza
    public Pizza(int number, String name, String description, double price) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Real go Getters, de tillader andre klasser at tilgå værdien fra private uden det kan ændres
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    //Set trippin' Setters, de ændre private felter på en kontrolleret måde.
    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Overrider og laver en ny version af to string Method(Man)
    @Override
    // to string gør det lettere at læse så man ikke får hexadecimal
    public String toString() {
        return "#" + number + "" + name + ": " + description + "......" + price + " kr.";
    }
}
