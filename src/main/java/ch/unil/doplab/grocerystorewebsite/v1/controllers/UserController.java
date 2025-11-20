package ch.unil.doplab.grocerystorewebsite.v1.controllers;

import ch.unil.doplab.grocerystorewebsite.v1.exceptions.AlreadyExistsException;
import ch.unil.doplab.grocerystorewebsite.v1.exceptions.DoesNotExistException;
import ch.unil.doplab.grocerystorewebsite.v1.models.User;
import ch.unil.doplab.grocerystorewebsite.v1.database.MockDatabase;
import ch.unil.doplab.grocerystorewebsite.v1.exceptions.InsufficientBalanceException;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class UserController {

    private static String username = "";
    private static String firstName = "";
    private static String lastName = "";
    private static String email = "";
    private static String password = "";
    private static double amount = 0.0;

    public static void createAUser() {
        try {
            if (!emailExists() && !usernameExists()) {
                MockDatabase.addAUser(new User(username, firstName, lastName, email, password));
            }
        } catch (AlreadyExistsException | DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void depositMoney() {
        LoginController.getUserLoggedIn().increaseBalance(amount);
    }

    public static void completeShopping() {
        try {
            LoginController.getUserLoggedIn().completeShopping();
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected static User findByUsername(String username) throws DoesNotExistException {
        for (User user : MockDatabase.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

    protected static boolean emailExists() throws AlreadyExistsException {
        for (User user : MockDatabase.getUsers()) {
            if (user.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }

    protected static boolean usernameExists() throws DoesNotExistException {
        for (User user : MockDatabase.getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static double getAmount() {
        return amount;
    }

    public static String getEmail() {
        return email;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setAmount(double amount) {
        UserController.amount = amount;
    }

    public static void setEmail(String email) {
        UserController.email = email;
    }

    public static void setFirstName(String firstName) {
        UserController.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        UserController.lastName = lastName;
    }

    public static void setPassword(String password) {
        UserController.password = password;
    }

    public static void setUsername(String username) {
        UserController.username = username;
    }

}
