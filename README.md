# Marios Pizzabar

## Projektbeskrivelse

Marios Pizzabar er et Java-baseret bestillingssystem udviklet til Mario, der driver en lille pizzabar på Nørrebrogade.
Systemet er udviklet som et 1. semester projekt på datamatikeruddannelsen.

Systemet kører lokalt og offline på Marios Dell-laptop og håndterer følgende:

- Oprettelse og visning af pizzabestillinger
- Sortering af bestillinger efter afhentingstidspunkt
- Tre kundetyper med forskellige rabatsatser (Normal, VIP, Medarbejder)
- Systemet kan gemme ekspederede ordrer til fil (orders.csv)
- Indlæsning af menukort fra fil (menu.txt)

---

## Projektstruktur

```
MariosPizzabar
│
├── main
│     PizzaBarApp.java
│
├── ui
│     PizzaBarUI.java
│
├── model
│     Customer.java
│     NormalCustomer.java
│     VIPCustomer.java
│     EmployeeCustomer.java
│     Pizza.java
│     OrderStatus.java
│
├── service
│     Order.java
│     OrderHandler.java
│
├── file
│     FileHandler.java
│
└── util
      ErrorHandler.java
      ExceptionHandler.java
      Comparators.java
```

---

## Pakker

### `main`
Indeholder programmets indgangspunkt. Starter applikationen ved at oprette og kalde `PizzaBarUI`.

### `ui`
Indeholder brugergrænsefladen. Håndterer al interaktion mellem brugeren (Alfonso/Mario) og systemet via en tekstbaseret konsol-menu.

### `model`
Indeholder systemets dataklasser. Disse klasser repræsenterer de centrale objekter i systemet — pizzaer, kunder og ordrestatus.

### `service`
Indeholder forretningslogikken. Håndterer oprettelse, beregning og styring af ordrer.

### `file`
Indeholder al fil-kommunikation. Ansvarlig for at læse menuen fra fil og gemme ekspederede ordrer til CSV.

### `util`
Indeholder hjælpeklasser der bruges på tværs af hele systemet. Håndterer brugerinput, fejlhåndtering og sortering.

---

## Klasser

### `main`

#### `PizzaBarApp`
Programmets startpunkt. Indeholder `main()`-metoden der opretter en instans af `PizzaBarUI` og kalder `start()`.

---

### `ui`

#### `PizzaBarUI`
Styrer hele brugergrænsefladen via en løbende konsol-menu. Alfonso bruger denne klasse til at oprette og håndtere bestillinger. Klassen kommunikerer med `OrderHandler` for ordrestyring og `FileHandler` for menuindlæsning.

---

### `model`

#### `Customer` *(abstrakt)*
Superklasse for alle kundetyper. Indeholder kundenavn og den abstrakte metode `getDiscountRate()` som alle subklasser skal implementere.

#### `NormalCustomer`
Almindelig kunde uden rabat. `getDiscountRate()` returnerer `0.0`.

#### `VIPCustomer`
Loyal kunde med 10% rabat. `getDiscountRate()` returnerer `0.10`.

#### `EmployeeCustomer`
Medarbejder med 20% rabat. `getDiscountRate()` returnerer `0.20`.

#### `Pizza`
Repræsenterer en pizza på menukortet med nummer, navn, beskrivelse og pris. Indeholder getters, setters og `toString()`.

#### `OrderStatus` *(enum)*
Definerer de mulige tilstande en ordre kan befinde sig i:
- `ORDER_PLACED` — ordre er oprettet
- `ORDER_IN_PROGRESS` — ordre er under tilberedning
- `ORDER_READY_FOR_PICKUP` — ordre er klar til afhentning
- `ORDER_COMPLETED` — ordre er afhentet og betalt

---

### `service`

#### `Order`
Repræsenterer en enkelt bestilling med ordre-ID, afhentingstidspunkt, pizzaarray, kunde og status. Beregner den samlede pris med rabat via `calculateTotalPrice()` og kan formateres til CSV via `toCSV()`.

#### `OrderHandler`
Styrer alle aktive og afsluttede ordrer. Håndterer oprettelse, fjernelse og fuldførelse af ordrer samt sortering efter afhentingstidspunkt via `Comparators`.

---

### `file`

#### `FileHandler`
Læser menukortet fra `menu.txt` ved opstart og gemmer ekspederede ordrer til `orders.csv`. Bruger `ExceptionHandler` ved fil-fejl.

---

### `util`

#### `ErrorHandler`
Sikrer korrekt brugerinput ved at validere og returnere `int` og `String` værdier. Forhindrer at programmet crasher ved forkert input fra Alfonso.

#### `ExceptionHandler`
Fejlhåndtering for hele systemet. Håndterer fejl som manglende ordrer, filer der ikke kan findes, ugyldigt input og array-overskridelser.

#### `Comparators`
Implementerer `Comparator<Order>` og sorterer ordrer efter afhentingstidspunkt via `LocalDateTime.compareTo()`. Bruges af `OrderHandler.sortOrdersByTime()`.
