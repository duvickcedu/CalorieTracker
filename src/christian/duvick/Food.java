package christian.duvick;

import java.io.Serializable;

public class Food implements Serializable {
    private final String name;
    private final Integer calories;

    public String getName() {
        return name;
    }

    public Integer getCalories() {
        return calories;
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
