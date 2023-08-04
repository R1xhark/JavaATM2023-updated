import java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 public class LibertyDatabaseConnector(){

    private String jdbcHost="hostname";
    private String dbPassword="password";
    private String dbUsername="user";
    bool clientCreated=false;

    public void AddClient(int pin, int id, string name,int cardnumber){
    
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connector = DriverManager.getConnection(jdbcHost, dbPassword, dbUsername);

            newClient Klient=new newClient(pin,id,name,cardnumber);
        
            String insertQuery = "INSERT INTO ClientInformation (client_name, card_number, pin,client_id) VALUES (?, ?, ?, ?)";
            PreparedStatement inserter = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, cardNumber);
            preparedStatement.setInt(3, pin);
            preparedStatement.setInt(4, id);
     
        
            int affected = inserter.executeUpdate();
            if (rowsAffected > 0) {
            System.out.println("Client byl úspěšně vytvořen!");
            clientCreated=true;
             
            }  

            inserQuery.close();
            connector.close();
       }
            catch (ClassNotFoundException | SQLException e) {
            System.out.println("Uzivatel nebyl vytvoren");
            e.printStackTrace();
       }
    }
 
