package com.mycompany.banksim;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankSim {

    public static void main(String[] args) {
        ATM atm = new ATM();
        LibertyDatabaseConnector firstRun=new LibertyDatabaseConnector();
        NewClient klient = new NewClient();
        Scanner read = new Scanner(System.in);

        // firstRun zkontroluje jestli je v databazi tabulka se kterou program pracuje..
        System.out.println("------------------");
        System.out.println("Nacitam databazy");
        firstRun.createTableInDatabase();
        System.out.println("");
        
        System.out.println("Co si prejes?");
        System.out.println("1. Vytvor clienta");
        System.out.println("2. Emuluj ATM");
        System.out.println("------------");
        System.out.println("Tvuj vyber?");
        
        try {
            int mainMenuInput = read.nextInt();
            read.nextLine(); 
            switch (mainMenuInput) {
                case 1 -> klient.createAcc();
                case 2 -> atm.start();
                default -> System.out.println("Neplatna volba. Zvolte 1 pro vytvoreni clienta nebo 2 pro emulaci ATM.");
            }
   } catch (InputMismatchException e) {
    System.out.println("Neplatny vstup. Zadejte platne cislo.");
}
    }
}
