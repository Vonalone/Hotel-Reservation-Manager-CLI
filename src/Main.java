import java.util.Scanner;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        user u = new user();
        int choice;
        do {
            s = new Scanner(System.in);
            System.out.println("1.REGISTRE");
            System.out.println("2.AUTHENTICATE");
            System.out.println("3.EXIT");
            System.out.print("Enter your choice: ");
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    s = new Scanner(System.in);
                    System.out.println("Enter your first name:");
                    String firstName = s.nextLine();
                    System.out.println("Enter your last name:");
                    String lastName = s.nextLine();
                    System.out.println("Enter your Email:");
                    String Email = s.nextLine();
                    System.out.println("Enter your phone number:");
                    String phoneNumber = s.nextLine();
                    System.out.println("Enter your CIN:");
                    String cin = s.nextLine();
                    System.out.println("Enter your password:");
                    String password = s.nextLine();
                    u = new user(firstName, lastName, Email, password, cin, phoneNumber);
                    if (!u.authenticate()) {
                        if (!u.checkCin(cin)) {
                            u.registre();
                        } else {
                            System.out.println("this CIN already using.");
                        }
                    } else {
                        System.out.println("You already have an account.");
                    }
                    break;
                case 2:
                    s = new Scanner(System.in);
                    System.out.println("Enter your cin:");
                    String cinUser = s.nextLine();
                    System.out.println("Enter your password:");
                    String passwordUser = s.nextLine();
                    u = new user(cinUser, passwordUser);
                    if (u.authenticate()) {
                        System.out.println("Success! You are logged in.");

                        if (u.is_admin()) {
                            s = new Scanner(System.in);
                            do {
                                System.out.println("Menu:");
                                System.out.println("1. Add room");
                                System.out.println("2. Change room price");
                                System.out.println("3. Display all rooms");
                                System.out.println("4. Delete room");
                                System.out.println("5. Display all reservations");
                                System.out.println("6. Delete reservation client");
                                System.out.println("7. Display all users");
                                System.out.println("8. Delete user");
                                System.out.println("9. add room from file");
                                System.out.println("10. change password");
                                System.out.println("11. Exit");
                                System.out.print("Enter your choice: ");
                                choice = s.nextInt();

                                switch (choice) {
                                    case 1:
                                        room.addRoom(u.userId());
                                        break;
                                    case 2:
                                        room.updateRoomPrice();
                                        break;
                                    case 3:
                                        room.displayAllRoom();
                                        break;
                                    case 4:
                                        room.deleteRoom();
                                        break;
                                    case 5:
                                        reservation.displayAllReservations();
                                        break;
                                    case 6:
                                        s = new Scanner(System.in);
                                        System.out.println("Enter cin user for delete him reservation");
                                        cin=s.nextLine();
                                        user ud = new user(cin);
                                        reservation.cancelReservation(ud.userId());
                                        break;
                                    case 7:
                                        user.displayAllUser();

                                        break;
                                    case 8:
                                        s = new Scanner(System.in);
                                        System.out.println("enter cin user:");
                                        cinUser = s.nextLine();
                                        if(u.checkCin(cinUser)){
                                            u.deleteUser(cinUser);
                                        } else {
                                            System.out.println("not found this account");
                                        }
                                        break;
                                    case 9:
                                        room.addRoomFromFile();
                                        break;
                                    case 10:
                                        u.changePassword();
                                        break;
                                    case 11:
                                        System.out.println("Exiting the menu. Goodbye!");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please select a valid option.");
                                }
                            } while (choice != 11);

                            s.close();
                        }

// user
                        else {
                            do {
                                System.out.println("Menu:");
                                System.out.println("1. Rooms Overview");
                                System.out.println("2. Cancel my reservation");
                                System.out.println("3. Display my reservation ");
                                System.out.println("4. Display my invoice ");
                                System.out.println("5. Add new reservation");
                                System.out.println("6. change password");
                                System.out.println("7. delete account");
                                System.out.println("8. Exit");
                                System.out.print("Enter your choice: ");
                                choice = s.nextInt();
                                switch (choice) {
                                    case 1:
                                        room.displayAllRoom();
                                        break;
                                    case 2:
                                        reservation.cancelReservation(u.userId());
                                        break;
                                    case 3:
                                        reservation.displayUserReservations(u.userId());
                                        break;
                                    case 4:
                                        invoice.DisplayInvoice(u.userId());
                                        break;
                                    case 5:
                                        reservation.AddReservation(u.userId());
                                        break;
                                    case 6:
                                        u.changePassword();
                                        break;
                                    case 7:
                                        s =new Scanner(System.in);
                                        System.out.println("enter your cin:");
                                        cinUser = s.nextLine();
                                        System.out.println("enter your password:");
                                        passwordUser = s.nextLine();
                                        user uTest = new user(cinUser, passwordUser);
                                        if (uTest.authenticate()) {
                                            u.deleteUser(cinUser);
                                            choice = 8;
                                        } else {
                                            System.out.println("CIN or Password is not correct");
                                        }
                                        break;
                                    case 8:
                                        System.out.println("Exiting the menu. Goodbye!");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please select a valid option.");
                                }
                            } while (choice != 8);
                            s.close();

                        }
                    } else {
                        System.out.println("error in authntiction");
                        if (u.checkCin(cinUser)) {
                            System.out.println("password is not correct");
                        } else {
                            System.out.println("cin or password is not correct");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting the menu. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 3);



    }

}