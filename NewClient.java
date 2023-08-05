package com.mycompany.banksim;

/**
 *
 * @author R1xhark
 */
import java.util.Scanner;
import java.util.Random;

public class NewClient {

    private String name;
    private int id;
    private long cardNumber;
    private int pin;
    private int age;

    public void createAcc() {
        try (Scanner read = new Scanner(System.in)) {
            System.out.println("Dobrý den. Vítejte v Liberty banks.");
            
            //input: Jmeno Klienta
            System.out.println("Zadejte vaše jméno");
            name = read.nextLine();
            // input pro vek klienta, klient mladsi 18 let preci nemuze mit ucet
            System.out.println("Zadejte prosím svůj věk");
            age = read.nextInt();
            
            // je li starsi 18 let muze si vytvorit ucet!
            if (age > 18 && age < 99 && age > 0) {
                Random randomizer=new Random();
                
                //Vygenereovani nahodneho 6-mistneho id
                id=randomizer.nextInt(1000000)+1000000;
                //Vygenerovani nahodneho 16-mistneho id
                cardNumber=(long) (randomizer.nextDouble() * 9000000000000000L) + 1000000000000000L;;
                
                System.out.println("Prosim vytvorte si 4 mistny pin:");
                pin=read.nextInt();
                
                int pinCheck=String.valueOf(pin).length();
                
                if(pinCheck==4){
                LibertyDatabaseConnector clientCreator=new LibertyDatabaseConnector();
                clientCreator.addClient(name, cardNumber, pin, id);
                }
                else{
                    System.out.println("Neplatny pin!!");
                }
            } else {
                System.out.println("Věk nesplňuje požadavky pro vytvoření účtu.");
            }
        }
    }
}
