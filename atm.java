import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ATM {
    public void atmOperator() {
        try {
            Scanner reader=new Scanner(System.in);
            
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://hostname:3306/YourDatabaseName";
            String dbUsername = "user";
            String dbPassword = "password";

            Connection atmConnector = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String query = "SELECT client_name, card_number, pin,balance FROM ClientInformation";
            PreparedStatement preparedStatement = atmConnector.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String clientName = resultSet.getString("client_name");
                int cardNumber = resultSet.getInt("card_number");
                int pin = resultSet.getInt("pin");
                double balance=resultSet.getDouble("balance");
                // Process the retrieved data as needed
                
                if ()
            }

            resultSet.close();
            preparedStatement.close();
            atmConnector.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
