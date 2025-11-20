package ch.unil.doplab.grocerystorewebsite.v1.views;

import ch.unil.doplab.grocerystorewebsite.v1.controllers.FoodController;
import ch.unil.doplab.grocerystorewebsite.v1.controllers.LoginController;
import ch.unil.doplab.grocerystorewebsite.v1.controllers.UserController;
import java.util.Scanner;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the SOAR Grocery Store!");
        homePage();
    }

    private static void homePage() {
        String choice, username, password, firstName, lastName, email;
        do {
            System.out.println("Enter:"
                    + "\n[q] to quit the application"
                    + "\n[1] to login"
                    + "\n[2] to create a user account"
                    + "\n[3] to see products in the store");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter your username:");
                    username = sc.nextLine();
                    System.out.println("Enter your password:");
                    password = sc.nextLine();
                    LoginController.setUsername(username);
                    LoginController.setPassword(password);
                    LoginController.userLogsIn();
                    if (LoginController.getUserLoggedIn() != null) {
                        userHomePage();
                    }
                    break;
                case "2":
                    System.out.println("Enter a username:");
                    username = sc.nextLine();
                    System.out.println("Enter a first name:");
                    firstName = sc.nextLine();
                    System.out.println("Enter a last name:");
                    lastName = sc.nextLine();
                    System.out.println("Enter an email:");
                    email = sc.nextLine();
                    System.out.println("Enter a password:");
                    password = sc.nextLine();
                    UserController.setUsername(username);
                    UserController.setFirstName(firstName);
                    UserController.setLastName(lastName);
                    UserController.setEmail(email);
                    UserController.setPassword(password);
                    UserController.createAUser();
                    break;
                case "3":
                    System.out.println(FoodController.getFoods());
                    break;
                case "q":
                    System.out.println("Quitting...");
                    break;
                default:
                    System.out.println("Choice = " + choice + " does not exist.");
                    break;
            }
        } while (!choice.equals("q"));
    }

    public static void userHomePage() {
        String choice, subChoice, foodName;
        double newBalance;
        do {
            System.out.println("Enter:"
                    + "\n[q] to log out"
                    + "\n[1] to see products in the store and add products"
                    + "\n[2] to remove a food from the shopping cart"
                    + "\n[3] to see products in the shopping cart"
                    + "\n[4] to deposit money to account"
                    + "\n[5] to complete shopping"
                    + "\n[6] to show user information");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(FoodController.getFoods());
                    do {
                        System.out.println("Enter: "
                                + "\n[q] to go back"
                                + "\n[1] to add food from store to the shopping cart");
                        subChoice = sc.nextLine();
                        switch (subChoice) {
                            case "1":
                                System.out.println("Enter the name of the food:");
                                foodName = sc.nextLine();
                                FoodController.setFoodName(foodName);
                                FoodController.addFoodToShoppingCart();
                                break;
                            case "q":
                                break;
                            default:
                                System.out.println("Choice = " + subChoice + " does not exist.");
                                break;
                        }
                    } while (!subChoice.equals("q"));
                    break;
                case "2":
                    System.out.println("Here are the products in the shopping cart, write the name of the food you want to remove.");
                    System.out.println(LoginController.getUserLoggedIn().getShoppingCart().toString());
                    System.out.println("Food name:");
                    foodName = sc.nextLine();
                    FoodController.setFoodName(foodName);
                    FoodController.removeFoodFromShoppingCart();
                    break;
                case "3":
                    System.out.println(LoginController.getUserLoggedIn().getShoppingCart().toString());
                    break;
                case "4":
                    System.out.println("Enter the amount you want to deposit (double):");
                    newBalance = sc.nextDouble();
                    sc.nextLine(); // nextDouble() takes the double but does not consume the \n comes after the value, we must consume it
                    UserController.setAmount(newBalance);
                    UserController.depositMoney();
                    break;
                case "5":
                    UserController.completeShopping();
                    break;
                case "6":
                    System.out.println(LoginController.getUserLoggedIn().toString());
                    break;
                case "q":
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Choice = " + choice + " does not exist.");
                    break;
            }
        } while (!choice.equals("q"));
        LoginController.userLogsout();
    }

}
