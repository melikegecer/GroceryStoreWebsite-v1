package ch.unil.doplab.grocerystorewebsite.v1.models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class Food {

    private String name;
    private double price;
    private ArrayList<String> ingredients;

    public Food(String name, double price, ArrayList<String> ingredientList) {
        this.name = name;
        this.price = price;
        ingredients = new ArrayList<>();
        ingredients.addAll(ingredientList);
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Food) {
            Food f = (Food) obj;
            return f.toString().equals(this.toString());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Food{"
                + "\nName=" + name
                + "\nPrice=" + price
                + "\nIngredients=" + Arrays.toString(ingredients.toArray()) + '}';
    }
}
