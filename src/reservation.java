import java.io.FileWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class reservation {

        private int reserve_id;
        private int user_reserv_id;
        private int room_reserv_id;
        private String check_in_date;
        private String check_out_date;
        private int person_number;


    public reservation(int reserve_id, int user_reserv_id, int room_reserv_id, String check_in_date, String check_out_date, int person_number) {
        this.reserve_id = reserve_id;
        this.user_reserv_id = user_reserv_id;
        this.room_reserv_id = room_reserv_id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.person_number = person_number;
    }


        public static void AddReservation(int user_reserv_id){
            try{
                Connection con = ConnexionJV.openConnection();
                try{

                con.setAutoCommit(false);
                System.out.println("Creating your reservation...");
                String sql ="INSERT INTO reservation (user_reserv_id,room_reserv_id,check_in_date,check_out_date, person_number) VALUES(?,?,?,?,?);";
                PreparedStatement PrStmt = con.prepareStatement(sql);
                Scanner s = new Scanner(System.in);
                System.out.println("enter number room  :");
                int number_room = s.nextInt();
                System.out.println("enter the date in:");
                String checkInDate = s.next();
                System.out.println("enter the date out :");
                String checkOutDate = s.next();
                System.out.println("enter the person number  :");
                int personNumber = s.nextInt();
                PrStmt.setInt(1,user_reserv_id);
                PrStmt.setInt(2,number_room);
                PrStmt.setString(3,checkInDate);
                PrStmt.setString(4,checkOutDate);
                PrStmt.setInt(5,personNumber);
                PrStmt.executeUpdate();
                System.out.println("Your reservation is complete");
                invoice.addInvoice(user_reserv_id,priceTotal(number_room,checkInDate,checkOutDate));
                con.commit();
                con.close();}catch (SQLException se){
                    System.out.println("Exist error please repeat reservation");
                    con.rollback();
                }

            }
            catch(Exception e){
                System.out.println(e);
            }


        }


        public static void displayAllReservations(){
            try{
                Connection con = ConnexionJV.openConnection();
                System.out.println("Reservation..");
                    String sql = "SELECT reserv_id,user_reserv_id,room_reserv_id,check_in_date,check_out_date,person_number FROM reservation ";
                    PreparedStatement PrStmt = con.prepareStatement(sql);
                    ResultSet rs = PrStmt.executeQuery();
                FileWriter fichierTxt = new FileWriter("user.txt", false) ;
                while(rs.next()){
                    int reserve_id=rs.getInt("reserv_id");
                    int user_reserv_id=rs.getInt("user_reserv_id");
                    int room_reserv_id=rs.getInt("room_reserv_id");
                    Date check_in_date = rs.getDate("check_in_date");
                    Date check_out_date = rs.getDate("check_out_date");
                    int person_number = rs.getInt("person_number");

                    System.out.println("_____________________________");
                    System.out.println("| reserve_id :"+reserve_id+"|");
                    System.out.println("| user_reserv_id :"+user_reserv_id+"|");
                    System.out.println("| room_reserv_id:"+room_reserv_id+"|");
                    System.out.println("| check_in_date :"+check_in_date+    "|");
                    System.out.println("| check_out_date :"+check_out_date+  "|");
                    System.out.println("| person_number :"+person_number+  "|");
                    System.out.println("_____________________________");


                    String line="| reserve_id :"+reserve_id+"| user_reserv_id :"+user_reserv_id+"| room_reserv_id:"+room_reserv_id+"| check_in_date :"+check_in_date+ "| check_out_date :"+check_out_date+ "| person_number :"+person_number+  "|";
                    fichierTxt.write(line);
                    System.out.println("Export completed.");
                }
                con.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

        public static void displayUserReservations( int userId) {
            try {
                Connection con = ConnexionJV.openConnection();
                System.out.println(" ----- your Reservation -----");
                     String sql = "SELECT reserv_id, user_reserv_id, room_reserv_id, check_in_date, check_out_date, person_number FROM reservation WHERE user_reserv_id = ?" ;
                     PreparedStatement PrStmt =con.prepareStatement(sql);
                     PrStmt.setInt(1,userId);
                     ResultSet rs = PrStmt.executeQuery();
                     boolean foundReservations = false;
                    while (rs.next()) {
                        foundReservations = true;
                        int reserve_id = rs.getInt("reserv_id");
                        int user_reserv_id = rs.getInt("user_reserv_id");
                        int room_reserv_id = rs.getInt("room_reserv_id");
                        Date check_in_date = rs.getDate("check_in_date");
                        Date check_out_date = rs.getDate("check_out_date");
                        int person_number = rs.getInt("person_number");

                        System.out.println("_____________________________");
                        System.out.println("| reserve_id: " + reserve_id + "|");
                        System.out.println("| user_reserv_id: " + user_reserv_id + "|");
                        System.out.println("| room_reserv_id: " + room_reserv_id + "|");
                        System.out.println("| check_in_date: " + check_in_date + "|");
                        System.out.println("| check_out_date: " + check_out_date + "|");
                        System.out.println("| person_number: " + person_number + "|");
                        System.out.println("_____________________________");

                    }if (!foundReservations) {
                    System.out.println("You don't have any reservations.");
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static void cancelReservation(int userId){
        try {
            Connection con = ConnexionJV.openConnection();
            Scanner s = new Scanner(System.in);
            String sql = "DELETE FROM reservation where reserv_id=? AND user_reserv_id=?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            System.out.println("enter the number reservation :");
            int ReservationNumber = s.nextInt();
            PrStmt.setInt(1,ReservationNumber);
            PrStmt.setInt(2,userId);
            System.out.println("Delete reservation ...");
            PrStmt.executeUpdate();
            System.out.println("Delete reservation successful! ");
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

        public static int NumberDays(String inDate,String outDate){
            LocalDate localDateIn = LocalDate.parse(inDate);
            LocalDate localDateOut = LocalDate.parse(outDate);
            Period period = Period.between(localDateIn,localDateOut);
            return Math.abs(period.getDays());
        }
        public static double priceTotal(int NbRoom,String inDate,String outDate){
        return NumberDays(inDate,outDate)*room.price_room(NbRoom);
        }
    }




