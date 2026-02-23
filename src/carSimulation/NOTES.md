# Principles

## Single Responsibility Principle

- `CarController.java`: Hanterar alla bilar och verkstäder, dvs all logik för det `CarView` visar, hanterar även att modifiera bilarnas state och att uppdatera deras position. Hanterar även workshop logik, alltså att ladda på bilar i VolvoWorkshop.
- `CarView.java`: Hanterar användargränssnittet för de objekt som finns definierade i `CarController.java`, hanterar i princip knapparna och själva fönstret
- `DrawPanel.java` Hanterar de grafiska representationerna av objekten i `CarController.java` och hur dem ritas, ideellt så bör ritningen av bilarna och deras grafiska positionering vara separat.

`CarController.java` följer inte _Single Responsibility Principle_ lika väl som de andra och hade tänkbart kunnat delas upp.

## Separation of Concerns



## Open Closed Principle

Klasserna i allmänhet följer inte _Open Closed Principle_ särskilt väl eftersom man måste ändra i dem för att utöka eller ändra programmets funktionalitet, exempelvis så behöver alla klasser ändras om man vill lägga till en ny bil och ha kontroller för den.

```java
if (car instanceof Volvo240)
```
är ett exempel på hur CarController bryter mot _Open Closed Principle_ eftersom en ny bil kräver ett nytt if statement mm.


## Law of Demeter

`CarController.java` använder `CarView.java`'s gränssnitt men endast för att komma åt `DrawPanel.java`, alltså pratar `CarController` med en "friend of friends" vilket bryter mot Law of Demeter


## Dependency Inversion Principle

Både `CarView.java` och `CarController.java` beror på konkreta implementationer av varandra och DrawPanel, alltså finns det inga beroenden på abstraktioner mellan klasserna.

 ```java
 if(car instanceof Saab95)
 ```

`CarController.java` beror även på konkreta implementationer av alla bilar och VolvoWorkshop vilket bryter mot _Dependency Inversion Principle_ eftersom det använder de konkreta implementationerna av alla bilar.