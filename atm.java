package com.mycompany.banksim;

/**
 *
 * @author R1xhark
 */
import java.util.Scanner;

public class ATM {
    private final LibertyDatabaseConnector connector;

    public ATM() {
        connector = new LibertyDatabaseConnector();
    }

    public void start() throws SQLExeption {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("LibertyBank ATM Menu");
            System.out.println("Vitejte!");
            System.out.println("1. Vybery");
            System.out.println("2. Zustatek");
            System.out.println("3. Exit");
            System.out.print("Vase Volba: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Zadejte cislo karty: ");
                    long withdrawCardNumber = scanner.nextLong();
                    System.out.print("Kolik Chcete vybrat?: ");
                    int withdrawAmount = scanner.nextInt();
                    connector.vyber(withdrawCardNumber, withdrawAmount);
                }

                case 2 -> {
                    System.out.print("Vlozte LibertyBanks Kartu: ");
                    long checkCardNumber = scanner.nextLong();
                    connector.printBalance(checkCardNumber);
                }

                case 3 -> {
                    System.out.println("Ukoncuji ATM, dekuji.");
                    return;
                }

                default -> System.out.println("Bohuzel takovou volbu zatim neznam :) vyberte znovu");
            }
        }
    }

    }
