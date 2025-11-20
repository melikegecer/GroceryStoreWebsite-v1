package ch.unil.doplab.grocerystorewebsite.v1.tests;

import ch.unil.doplab.grocerystorewebsite.v1.controllers.FoodController;
import ch.unil.doplab.grocerystorewebsite.v1.controllers.LoginController;
import ch.unil.doplab.grocerystorewebsite.v1.models.User;
import ch.unil.doplab.grocerystorewebsite.v1.database.MockDatabase;
import ch.unil.doplab.grocerystorewebsite.v1.exceptions.DoesNotExistException;
import ch.unil.doplab.grocerystorewebsite.v1.models.Food;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Timeout;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class FoodControllerTest {

    public FoodControllerTest() {
        System.out.println("FoodControllerTest constructor");
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
        System.out.println("@BeforeAll setUpClass");
        // we initialize our UserList (database for users) and add users to it
        MockDatabase.addAUser(new User("lisa", "lisa", "simpson", "lisa@simpson.com", "1234"));
        MockDatabase.addAUser(new User("homer", "homer", "simpson", "homer@simpson.com", "1234"));
        MockDatabase.addAUser(new User("marge", "marge", "simpson", "marge@simpson.com", "1234"));
        MockDatabase.addAUser(new User("bart", "bart", "simpson", "bart@simpson.com", "1234"));
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
        System.out.println("@AfterAll tearDownClass");
        MockDatabase.getUsers().clear();
    }

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("@BeforeEach setUp");
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("@AfterEach tearDown");
    }

    @Test
    public void testIndexOutOfBoundsException() {
        System.out.println("@Test testIndexOutOfBoundsException");
        IndexOutOfBoundsException assertThrows = assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    MockDatabase.getUsers().get(100);
                });
    }

    @Test
    public void testAddFoodToShoppingCart() throws DoesNotExistException {
        System.out.println("@Test testAddFoodToShoppingCart");
        // login as a user
        LoginController.setUsername("marge");
        LoginController.setPassword("1234");
        LoginController.userLogsIn();
        // we need some mock data
        MockDatabase.addFood(new Food("Pizza", 12.0, new ArrayList<String>() {
            {
                add("dough");
                add("tomato sauce");
                add("mozarella");
            }
        }));
        String foodName = "Pizza";
        // add the food to the shopping cart
        FoodController.setFoodName(foodName);
        FoodController.addFoodToShoppingCart();
        // find the food in the shopping cart
        Food expectedFood = null;
        for (Food f : LoginController.getUserLoggedIn().getShoppingCart().getFoods()) {
            if (f.getName().equals(foodName)) {
                expectedFood = f;
            }
        }
        // if the food exists, expectedFood must have a value
        assertNotNull(expectedFood);
    }

    @Test
    public void testRemoveFoodFromShoppingCart() throws DoesNotExistException {
        System.out.println("@Test testRemoveFoodFromShoppingCart");
        // login as a user
        LoginController.setUsername("marge");
        LoginController.setPassword("1234");
        LoginController.userLogsIn();
        // we need some mock data
        MockDatabase.addFood(new Food("Pizza", 12.0, new ArrayList<String>() {
            {
                add("dough");
                add("tomato sauce");
                add("mozarella");
            }
        }));
        String foodName = "Pizza";
        // remove the food
        FoodController.setFoodName(foodName);
        FoodController.removeFoodFromShoppingCart();
        // it should not exist in the shopping cart
        Food expectedFood = null;
        for (Food f : LoginController.getUserLoggedIn().getShoppingCart().getFoods()) {
            if (f.getName().equals(foodName)) {
                expectedFood = f;
            }
        }
        // if the food does not exist, expectedFood must be null
        assertNull(expectedFood);
    }

    @Test
    @Timeout(1000)
    public void testTimeOut() throws InterruptedException {
        System.out.println("@Test testTimeOut");
        Thread.sleep(500);
    }

    @Test
    public void testNumberFormatException() {
        System.out.println("@Test testNumberFormatException");
        NumberFormatException assertThrows = assertThrows(NumberFormatException.class,
                () -> {
                    int n = Integer.parseInt(null);
                });
    }

    @Test
    public void testClassCastException() {
        System.out.println("@Test testClassCastException");
        ClassCastException assertThrows = assertThrows(ClassCastException.class,
                () -> {
                    Object o = Integer.valueOf(42);
                    String s = (String) o;
                });
    }

    @Test
    public void testArithmeticException() {
        System.out.println("@Test testArithmeticException");
        ArithmeticException assertThrows = assertThrows(ArithmeticException.class,
                () -> {
                    int result = 7 / 0;
                });
    }
}
