package ch.unil.doplab.grocerystorewebsite.v1.models;

import ch.unil.doplab.grocerystorewebsite.v1.exceptions.InsufficientBalanceException;
import java.util.Arrays;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class User {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private int password;
    private double balance;
    private ShoppingCart shoppingCart;

    public User(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password.hashCode();
        this.balance = 0;
        this.shoppingCart = new ShoppingCart();
    }

    public void completeShopping() throws InsufficientBalanceException {
        if (this.balance >= shoppingCart.getBalance()) {
            System.out.println("You bought foods=" + Arrays.toString(shoppingCart.getFoods().toArray()) + ".");
            balance -= shoppingCart.emptyShoppingCart();
        } else {
            throw new InsufficientBalanceException("Balance is not enough.");
        }
    }

    public void increaseBalance(double balance) {
        this.balance += balance;
    }

    public void decreaseBalance(double balance) {
        this.balance -= balance;
    }

    public double getBalance() {
        return balance;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public boolean isPasswordCorrect(String password) {
        return password.hashCode() == this.password;
    }

    @Override
    public boolean equals(Object obj) {
        return ((User) obj).getUsername().equals(this.username);
    }

    @Override
    public String toString() {
        return "Username: " + this.username
                + "\nFirst name: " + this.firstName
                + "\nLast name: " + this.lastName
                + "\nEmail: " + this.email
                + "\nBalance: " + this.balance
                + "\n" + this.shoppingCart.toString();
    }

}
