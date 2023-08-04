import java;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 public class LibertyDatabaseConnector(){

    private String jdbcHost="hostname";
    private String dbPassword="password";
    private String dbUsername="user";

   try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcHost, dbPassword, dbUsername);
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
    
