import java.sql.*;

public class ConnexionJV {
    public static Connection openConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataHotel", "root", "7007");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }


}