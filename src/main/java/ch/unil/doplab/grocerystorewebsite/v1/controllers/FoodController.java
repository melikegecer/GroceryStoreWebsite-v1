package ch.unil.doplab.grocerystorewebsite.v1.controllers;

import ch.unil.doplab.grocerystorewebsite.v1.exceptions.DoesNotExistException;
import ch.unil.doplab.grocerystorewebsite.v1.models.Food;
import ch.unil.doplab.grocerystorewebsite.v1.database.MockDatabase;
import ch.unil.doplab.grocerystorewebsite.v1.models.User;
import java.util.ArrayList;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class FoodController {

    private static String foodName = "";

    public static void addFoodToShoppingCart() {
        User user = LoginController.getUserLoggedIn();
        try {
            Food f = findFoodByNameInTheStore();
            user.getShoppingCart().addFood(f);
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void removeFoodFromShoppingCart() {
        User user = LoginController.getUserLoggedIn();
        try {
            if (doesFoodExistInShoppingCart()) {
                user.getShoppingCart().removeFood(findFoodByNameInShoppingCart());
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected static boolean doesFoodExistInShoppingCart() {
        for (Food f : LoginController.getUserLoggedIn().getShoppingCart().getFoods()) {
            if (f.getName().equals(foodName)) {
                return true;
            }
        }
        return false;
    }

    protected static Food findFoodByNameInTheStore() throws DoesNotExistException {
        for (Food f : MockDatabase.getFoods()) {
            if (f.getName().equals(foodName)) {
                return f;
            }
        }
        throw new DoesNotExistException("Food " + foodName + " does not exist.");
    }

    protected static Food findFoodByNameInShoppingCart() throws DoesNotExistException {
        for (Food f : LoginController.getUserLoggedIn().getShoppingCart().getFoods()) {
            if (f.getName().equals(foodName)) {
                return f;
            }
        }
        throw new DoesNotExistException("Food " + foodName + " does not exist.");
    }

    public static ArrayList<Food> getFoods() {
        return MockDatabase.getFoods();
    }

    public static String getFoodName() {
        return foodName;
    }

    public static void setFoodName(String foodName) {
        FoodController.foodName = foodName;
    }

}
