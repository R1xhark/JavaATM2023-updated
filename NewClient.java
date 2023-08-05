package com.mycompany.banksim;

import java.util.Scanner;
import java.util.Random;

public class NewClient {

    private String name;
    private int id;
    private long cardNumber;
    private int age;

    public void createAcc() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Dobrý den. Vítejte v Liberty banks.");

            //input: Jmeno Klienta
            System.out.println("Zadejte vaše jméno");
            name = scanner.nextLine();

            // input pro vek klienta, klient mladsi 18 let preci nemuze mit ucet
            System.out.println("Zadejte prosím svůj věk");
            age = scanner.nextInt();

            // je li starsi 18 let muze si vytvorit ucet
            if (age > 18 && age < 99 && age > 0) {
                Random randomizer = new Random();

                //Vygenereovani nahodneho 6-mistneho id
                id = randomizer.nextInt(1000000) + 1000000;

                //Vygenerovani nahodneho 16-mistneho cisla bankovni karty
                cardNumber = (long) (randomizer.nextDouble() * 9000000000000000L) + 1000000000000000L;

                // Bezpecnejsi input mechanismus pro PIN
                int pin = secureInputForPin(scanner);

                // pridani klienta do databaze
                LibertyDatabaseConnector clientCreator = new LibertyDatabaseConnector();
                clientCreator.addClient(name, cardNumber, pin, id);
            } else {
                System.out.println("Věk nesplňuje požadavky pro vytvoření účtu."); // nesplnuje vek > 18
            }
        }
    }

    // bezpecne zadani pinu
    private int secureInputForPin(Scanner scanner) {
        int pin = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.println("Prosím vytvořte si 4-místný PIN:");
            String pinString = scanner.next();
            
            if (pinString.matches("\\d{4}")) {
                pin = Integer.parseInt(pinString);
                validInput = true;
            } else {
                System.out.println("Neplatný PIN. Zadejte znovu.");
            }
        }
        
        return pin;
    }
}
