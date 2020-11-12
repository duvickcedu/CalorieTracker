package christian.duvick;

import java.io.Serializable;
import java.util.LinkedList;

public class FoodList implements Serializable {
    private LinkedList<Food> foods = new LinkedList<>();
    private static FoodList instance = null;

    public static FoodList getInstance() {
        if (instance == null) {
            instance = new FoodList();
        }
        return instance;
    }

    public void retrieve(FoodList loadedFoods) {
        instance = loadedFoods;
        foods = instance.getFoodList();
    }

    public int getCalorieTotal() {
        int total = 0;
        for (Food food: foods) {
            total += food.getCalories();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Food food: foods) {
            stringBuilder.append(food.getName()).append(' ');
        }
        return "FoodList{" +
                "foods=" + stringBuilder.toString() +
                '}';
    }

    public LinkedList<Food> getFoodList() {
        return foods;
    }


    public void addFood(Food food) {
        foods.add(food);
    }
}
