import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.*;
import java.util.Scanner;

public class room {
    private int roomNumber;
    private String typeRoom;
    private double nightPrice;






    public room(int roomNumber, String typeRoom, double nightPrice) {
        this.roomNumber = roomNumber;
        this.typeRoom = typeRoom;
        this.nightPrice = nightPrice;
    }



    public static void addRoom(int id_admin) {
        try {
            Connection con = ConnexionJV.openConnection();

            Scanner s=new Scanner(System.in);
            int roomCount=0;
            String choice ="yes";
            System.out.println("ADD NEW ROOM ...");
            String sql = "INSERT INTO ROOM (room_number,type_room,night_price,id_admin) VALUES (?,?,?,?);";
            PreparedStatement preparedStatement= con.prepareStatement(sql);
            while(choice.equals("yes")) {
                System.out.println("  --- Add room " + ++roomCount + "  --- ");
                System.out.println("Enter room number : ");
                int roomNb = s.nextInt();
                System.out.println("Enter room type   : ");
                String typeRoom = s.next();
                System.out.println("Enter night price  : ");
                double nightPrice = s.nextDouble();
                preparedStatement.setInt(1, roomNb);
                preparedStatement.setString(2, typeRoom);
                preparedStatement.setDouble(3, nightPrice);
                preparedStatement.setInt(4, id_admin);
                preparedStatement.executeUpdate();
                System.out.println("Room saved successfully.    ");
                System.out.println("Add another room ? (yes/no) ");
                choice = s.next();
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // add room exist in file
    public static void addRoomFromFile() {
        try {
            Connection con = ConnexionJV.openConnection();
            System.out.println("Adding New Rooms...");

            String sql = "INSERT INTO ROOM (room_number, type_room, night_price, id_admin) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(sql);

            FileInputStream file = new FileInputStream("roomInsert.txt");
            Scanner scanner = new Scanner(file);
            int roomCount = 0;

            System.out.println("Room list taken from file 'room.txt':");
            System.out.println("room_number\t|\ttype_room\t|\tnight_price\t|\tid_admin");

            while (scanner.hasNextLine()) {
                System.out.println("--- Adding room " + ++roomCount + " ---");
                String roomNb = scanner.next();
                String typeRoom = scanner.next();
                float nightPrice = scanner.nextFloat();
                int idAdmin = scanner.nextInt();

                statement.setString(1, roomNb);
                statement.setString(2, typeRoom);
                statement.setFloat(3, nightPrice);
                statement.setInt(4, idAdmin);
                statement.executeUpdate();

                System.out.println("Room saved successfully.");
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // display room
    public static void displayAllRoom() {

        try {
            Connection con = ConnexionJV.openConnection();
            String sql ="SELECT room_number,type_room,night_price FROM room";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            FileWriter fichierTxt = new FileWriter("room.txt", false) ;
            while (rs.next()) {
                int roomNumber = rs.getInt("room_number");
                String typeRoom = rs.getString("type_room");
                double priceRoom = rs.getDouble("night_price");
                // affiche values in console
                System.out.println("_____________________________");
                System.out.println("| Number Room :" + roomNumber + "|");
                System.out.println("| Type Room :" + typeRoom + "|");
                System.out.println("| Price Room :" + priceRoom + "|");
                System.out.println("_____________________________");
                // affiche values in file
                String line="| Number Room :" + roomNumber +"| Type Room :" + typeRoom +"| Price Room :" + priceRoom + "|\n";
                fichierTxt.write(line);
            }
            System.out.println("Export completed.");
            fichierTxt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    //update room
    public static void updateRoomPrice() {
        try {
            Connection con = ConnexionJV.openConnection();
            Scanner s =new Scanner(System.in);
            String sql = "UPDATE room SET night_price =? where room_number= ? ";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            System.out.println("Enter room number : ");
            int roomNb = s.nextInt();
            System.out.println("Enter new night price:");
            Double newPrice = s.nextDouble();
            PrStmt.setDouble(1,newPrice);
            PrStmt.setInt(2,roomNb);
            System.out.println("Updating room price ...");
            PrStmt.executeUpdate();
            System.out.println("Price update successful!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // delete room
    public static void deleteRoom() {
        try {
            Connection con = ConnexionJV.openConnection();
            Scanner s = new Scanner(System.in);
            String sql = "DELETE FROM room where room_number=?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            System.out.println("Enter room number : ");
            int roomNb = s.nextInt();
            PrStmt.setInt(1,roomNb);
            System.out.println("Delete room ...");
            PrStmt.executeUpdate();
            System.out.println("Delete successful!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static float price_room(int roomNb){
        float price;
        try {
            Connection con = ConnexionJV.openConnection();
            Scanner s =new Scanner(System.in);
            String sql = "select night_price FROM room where room_number= ?";
            PreparedStatement PrStmt = con.prepareStatement(sql);
            PrStmt.setInt(1,roomNb);
            ResultSet rs =PrStmt.executeQuery();
            if (rs.next()) {
                price = rs.getFloat("night_price");
                return price;
            }

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }
    }

