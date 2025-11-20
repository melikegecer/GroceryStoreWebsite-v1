package ch.unil.doplab.grocerystorewebsite.v1.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class ShoppingCart {

    private ArrayList<Food> foods;
    private double balance;

    public ShoppingCart() {
        this.foods = new ArrayList<>();
        this.balance = 0.0;
    }

    public double emptyShoppingCart() {
        foods.clear();
        double tmp = balance;
        balance = 0.0;
        return tmp;
    }

    public double getBalance() {
        return balance;
    }

    public void addFood(Food food) {
        foods.add(food);
        balance += food.getPrice();
    }

    public void removeFood(Food food) {
        foods.remove(food);
        balance -= food.getPrice();
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ShoppingCart) {
            ShoppingCart sc = (ShoppingCart) obj;
            if (sc.getBalance() == balance) {
                ArrayList<String> scFoods = new ArrayList<>();
                for (Food f : foods) {
                    scFoods.add(Objects.toString(f, null));
                }
                ArrayList<String> thisSC = new ArrayList<>();
                for (Food f : foods) {
                    thisSC.add(Objects.toString(f, null));
                }
                Collections.sort(scFoods);
                Collections.sort(thisSC);
                if (scFoods.containsAll(thisSC)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ShoppingCart: " + Arrays.toString(foods.toArray());
    }

}
