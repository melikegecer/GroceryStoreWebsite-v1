package ch.unil.doplab.grocerystorewebsite.v1.database;

import ch.unil.doplab.grocerystorewebsite.v1.models.Food;
import ch.unil.doplab.grocerystorewebsite.v1.models.User;
import java.util.ArrayList;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class MockDatabase {

    private static ArrayList<User> users = new ArrayList<User>() {
        {
            add(new User("lisa", "lisa", "simpson", "lisa@simpson.com", "1234"));
            add(new User("homer", "homer", "simpson", "homer@simpson.com", "1234"));
            add(new User("marge", "marge", "simpson", "marge@simpson.com", "1234"));
            add(new User("bart", "bart", "simpson", "bart@simpson.com", "1234"));
        }
    };
    private static ArrayList<Food> foods = new ArrayList<Food>() {
        {
            add(new Food("Pasta", 15.0, new ArrayList<String>() {
                {
                    add("spaghetti");
                    add("pesto sauce");
                    add("parmigiano");
                }
            }));
            add(new Food("Chicken Curry", 18.0, new ArrayList<String>() {
                {
                    add("chicken");
                    add("curry sauce");
                }
            }));
            add(new Food("Pizza", 12.0, new ArrayList<String>() {
                {
                    add("dough");
                    add("tomato sauce");
                    add("mozarella");
                }
            }));
        }
    };

    public static void addAUser(User user) {
        users.add(user);
    }

    public static void addFood(Food food) {
        foods.add(food);
    }

    public static void removeAUser(User user) {
        users.remove(user);
    }

    public static void removeFood(Food food) {

    }

    public static ArrayList<Food> getFoods() {
        return foods;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

}
