package christian.duvick;

import java.io.Serializable;

public class Stats implements Serializable {
    Integer target = 2000;
    Integer deficit = 2000;
    private static Stats instance = null;

    public static Stats getInstance() {
        if (instance == null) {
            instance = new Stats();
        }
        return instance;
    }

    public Stats(Integer target) {
        this.target = target;
        this.deficit = target;
    }

    public Stats() {

    }

    public static void setInstance(Stats instance) {
        Stats.instance = instance;
    }

    public void retrieve(Stats loadedStats) {
        instance = loadedStats;
        target = instance.getTarget();
        deficit = instance.calculateDeficit();
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public int calculateDeficit() {
        deficit = getTarget() - FoodList.getInstance().getCalorieTotal();
        return deficit;
    }

    public void setDeficit(Integer deficit) {
        this.deficit = deficit;
    }
}
