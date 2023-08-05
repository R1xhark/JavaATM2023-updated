package com.mycompany.banksim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class LibertyDatabaseConnector {

    private String jdbcHost = "jdbc:mysql://hostname/nazev_databaze";
    private String dbUsername = "uzivatel";
    private String dbPassword = "heslo";

    public Connection connector() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcHost, dbUsername, dbPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("Nenalezena třída ovladače.");
            e.printStackTrace();
        }
        return null;
    }

    public void addClient(String client_name, long card_number, int pin, int client_id) {
        try (Connection connect = connector()) {
            String insertQuery = "INSERT INTO InformaceOKlientech (client_name, card_number, pin, client_id) VALUES (?, ?, ?, ?)";
            PreparedStatement vkladac = connect.prepareStatement(insertQuery);

            vkladac.setString(1, client_name);
            vkladac.setLong(2, card_number);
            vkladac.setInt(3, pin);
            vkladac.setInt(4, client_id);
            
            Scanner cti = new Scanner(System.in);

            int ovlivneneRadky = vkladac.executeUpdate();
            if (ovlivneneRadky > 0) {
                System.out.println("Klient byl úspěšně vytvořen!");
                System.out.println("Chcete si vložit nějaké peníze?");
                String userChoice = cti.nextLine();
                if (userChoice.toLowerCase().equals("ano")) {

                    System.out.println("Kolik chcete vložit:");
                    int vklad = cti.nextInt();

                    if (vklad >= 0) {
                        PreparedStatement vlozpenize = connect.prepareStatement("INSERT INTO ClientInformation (balance) VALUE (?)");
                        vlozpenize.setInt(1, vklad);
                    } else {
                        System.out.println("Záporné částky bohužel nelze vkládat :)");
                    }
                }
            } else {
                System.out.println("Zkontrolujte svá data!");
            }
        } catch (SQLException e) {
            System.out.println("Nastala chyba.");
            e.printStackTrace();
        }
    }

    public void clientInfoATM(long card_number, int client_id, int pin) {
        // Implementace pro získání informací o klientovi na bankomatu
    }
}
