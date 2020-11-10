package christian.duvick;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private Integer calories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Food(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    @Override
    public String toString() {
        return name + ": " + calories + " calories";
    }
}
