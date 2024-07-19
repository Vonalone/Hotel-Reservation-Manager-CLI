import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class invoice {
    private int invoice_id;
    private String payment_date;
    private Double price;

    public invoice(int invoice_id, String payment_date, Double price) {
        this.invoice_id = invoice_id;
        this.payment_date = payment_date;
        this.price = price;
    }

    public static void addInvoice(int user_id, double price) {
        try {
            Connection con = ConnexionJV.openConnection();
            LocalDate myDate = LocalDate.now();
            String paymentDate = myDate.toString();
            System.out.println("ADD NEW invoice ...");
            String sql = "INSERT INTO invoice (invoice_user_id,payment_date,price) VALUES (?,?,?);";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setInt(1,user_id);
            PrStmt.setString(2,paymentDate);
            PrStmt.setDouble(3,price);
            PrStmt.executeUpdate();
            System.out.println("ivoice IS SAVE");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void DisplayInvoice(int userId){
        try{
            Connection con = ConnexionJV.openConnection();
            String sql = "SELECT invoice_id,invoice_user_id,price,payment_date FROM invoice where invoice_user_id=?";
            PreparedStatement PrStmt=con.prepareStatement(sql);
                PrStmt.setInt(1,userId);
            ResultSet rs = PrStmt.executeQuery();
            boolean foundInvoice = false;
                while(rs.next()){
                    foundInvoice = true;
                    int invoice_id=rs.getInt("invoice_id");
                    int invoice_user_id=rs.getInt("invoice_user_id");
                    float price=rs.getFloat("price");
                    Date payment_date = rs.getDate("payment_date");
                    // affiche values
                    System.out.println("_____________________________");
                    System.out.println("| invoice id :"+invoice_id+"|");
                    System.out.println("| invoice user id :"+invoice_user_id+"|");
                    System.out.println("| price total :"+price+"|");
                    System.out.println("| payment date :"+payment_date+    "|");
                    System.out.println("_____________________________");
                }
            if (!foundInvoice) {
                System.out.println("You don't have any invoice.");
            }
            con.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}