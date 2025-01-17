package BMSPROJECT;
import java.util.Scanner;

public class UserAction {
    public static User userLogin(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Corrected: Access the list of users via BMS.getUsersList()
        for (User user : BMS.getUsersList()) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                } else {
                    System.out.println("Login failed! Invalid password.");
                    return null;
                }
            }
        }
        System.out.println("Login failed! Invalid username.");
        return null;
    }

    public static void registerUser(Scanner scanner) {
        System.out.print("Enter a username to register: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        // Corrected: Access the list of users via BMS.getUsersList()
        for (User user : BMS.getUsersList()) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Please try a different username.");
                return;
            }
        }

        // Corrected: Access the list of users via BMS.getUsersList()
        BMS.getUsersList().add(new User(username, password));
        System.out.println("Registration successful! You can now log in.");
    }
}






