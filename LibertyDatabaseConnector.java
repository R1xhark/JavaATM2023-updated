import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

 public class LibertyDatabaseConnector{
    
    
    private String jdbcHost="hostname";
    private String dbPassword="password";
    private String dbUsername="user";

    public void AddClient(String client_name, long card_number, int pin,int client_id){
    
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
               try (Connection connector = DriverManager.getConnection(jdbcHost, dbPassword, dbUsername)) {
               
               
               String insertQuery = "INSERT INTO ClientInformation (client_name, card_number, pin,client_id) VALUES (?, ?, ?, ?)";
               PreparedStatement inserter = connector.prepareStatement(insertQuery);
               
               PreparedStatement.setString(1, client_name);
               PreparedStatement.setLong(2, card_number);
               PreparedStatement.setInt(3, pin);
               PreparedStatement.setInt(4, Client_id);
               
               
               int affected = inserter.executeUpdate();
               if (rowsAffected > 0) {
                   System.out.println("Client byl úspěšně vytvořen!");
                   
               }
               
               insertQuery.close();
           }
       }
            catch (ClassNotFoundException | SQLException e) {
            System.out.println("Uzivatel nebyl vytvoren");
            e.printStackTrace();
       }
    }
 }

