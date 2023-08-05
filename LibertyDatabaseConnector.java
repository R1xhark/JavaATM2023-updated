package com.mycompany.banksim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LibertyDatabaseConnector {

    private final static String jdbcHost = "jdbc:mysql://hostname/nazev_databaze";
    private final static String dbUsername = "uzivatel";
    private final static String dbPassword = "heslo";

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
            String insertQuery = "INSERT INTO Client_info (client_name, card_number, pin, client_id) VALUES (?, ?, ?, ?)";
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

    public void clientInfoATM(long card_number, int client_id, int pin) throws SQLException {
    try (Connection connect = connector()) {
        String printQuery = "SELECT * FROM Client_info WHERE card_number = ? AND client_id = ? AND pin = ?";
        PreparedStatement printer = connect.prepareStatement(printQuery);

        printer.setLong(1, card_number);
        printer.setInt(2, client_id);
        printer.setInt(3, pin);

        ResultSet resultSet = printer.executeQuery();

        if (resultSet.next()) {
            
            String clientName = resultSet.getString("client_name");
            long retrievedCardNumber = resultSet.getLong("card_number");
            int retrievedPin = resultSet.getInt("pin");
            int retrievedClientId = resultSet.getInt("client_id");
            int zustatek=resultSet.getInt("balance");

            System.out.println("Client Name: " + clientName);
            System.out.println("Card Number: " + retrievedCardNumber);
            System.out.println("PIN: " + retrievedPin);
            System.out.println("Client ID: " + retrievedClientId);
            System.out.println("Zustatek je cini: " + zustatek);
            
        } else {
            System.out.println("Takovy client neni v databazy");
        }
    } catch (SQLException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
}
   public void vyber(long card_number, int amountToWithdraw) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    final int maxAttempts = 3;
    boolean pinMatched = false;

    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
        System.out.print("Vlozte Pin (" + attempt + "/" + maxAttempts + "): ");
        int enteredPin = scanner.nextInt();
        
        try (Connection connect = connector()) {
            String checkPinQuery = "SELECT balance FROM Client_info WHERE card_number = ? AND pin = ?";
            PreparedStatement checkPinStatement = connect.prepareStatement(checkPinQuery);
            checkPinStatement.setLong(1, card_number);
            checkPinStatement.setInt(2, enteredPin);

            ResultSet resultSet = checkPinStatement.executeQuery();

            if (resultSet.next()) {
                pinMatched = true;
                int currentBalance = resultSet.getInt("balance");
                
                if (amountToWithdraw > 0 && amountToWithdraw <= currentBalance) {
                    int newBalance = currentBalance - amountToWithdraw;

                    String updateBalanceQuery = "UPDATE Client_info SET balance = ? WHERE card_number = ?";
                    PreparedStatement updateBalanceStatement = connect.prepareStatement(updateBalanceQuery);
                    updateBalanceStatement.setInt(1, newBalance);
                    updateBalanceStatement.setLong(2, card_number);

                    int rowsUpdated = updateBalanceStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Vyber Dokoncen! Vas novy zustatek je: " + newBalance);
                    } else {
                        System.out.println("Vyber selhal, prosim podivejte se na stvrzenku.");
                        // kdybychom meli hardware co muze tisknout, vytisknuli bychom stvrzenku
                    }
                } else {
                    System.out.println("Neplatny vyber");
                }
                break;
            } else {
                System.out.println("Limit pokusu prekrocen.");
            }
        } catch (SQLException e) {
            System.out.println("error SQLExeption");
            e.printStackTrace();
        }
    }

    if (!pinMatched) {
        System.out.println("SPATNY PIN!! NEZBYVA ZADNY POKUS! BLOKUJI KARTU!");
    }
}
public void printBalance(long card_number) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    final int maxAttempts = 3;
    boolean pinMatched = false;

    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
        System.out.print("Vlozte Pin (" + attempt + "/" + maxAttempts + "): ");
        int enteredPin = scanner.nextInt();
        
        try (Connection connect = connector()) {
            String checkPinQuery = "SELECT balance FROM Client_info WHERE card_number = ? AND pin = ?";
            PreparedStatement checkPinStatement = connect.prepareStatement(checkPinQuery);
            checkPinStatement.setLong(1, card_number);
            checkPinStatement.setInt(2, enteredPin);

            ResultSet resultSet = checkPinStatement.executeQuery();

            if (resultSet.next()) {
                pinMatched = true;
                int currentBalance = resultSet.getInt("balance");
                System.out.println("Vas zustek cini: " + currentBalance);
                break;
            } else {
                System.out.println("Neplatny vyber");
            }
        } catch (SQLException e) {
            System.out.println("error SQLExeption");
            e.printStackTrace();
        }
    }

    if (!pinMatched) {
        System.out.println("SPATNY PIN!! NEZBYVA ZADNY POKUS! BLOKUJI KARTU!");
    }
}
public void createClientInfoTableIfNotExists() {
        try (Connection connection = connector()) {
            // kontrola jestli tabulka existuje
            boolean tableExists = false;
            try (ResultSet tables = connection.getMetaData().getTables(null, null, "Client_info", null)) {
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    if (tableName.equalsIgnoreCase("Client_info")) {
                        tableExists = true;
                        break;
                    }
                }
            }

            if (!tableExists) {
                // Precte si SQL soubor a pouzije ho jako querry
                String sqlFilePath = "C:\\Users\\richa\\Documents\\NetBeansProjects\\BankSim\\src\\main\\java\\com\\mycompany\\banksim\\LibertyBankDatabase.sql";
                String sqlQuery = readSqlFile(sqlFilePath);
                
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(sqlQuery);
                }
                
                System.out.println("Tabulka Client_info byla uspesne vytvorea ");
            } else {
                System.out.println("Tabulka client_info jiz v databazi existuje a je v poradku.");
            }
        } catch (SQLException e) {
            System.out.println("Pripojeni k SQL databazi selhalo.");
            e.printStackTrace();
        }
    }
    
    private String readSqlFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("soubor SQL neexistuje nebo je poskozeny.");
            e.printStackTrace();
        }
        return content.toString();
    }



}

}
    

