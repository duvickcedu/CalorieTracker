package christian.duvick;

import java.io.Serializable;

public class Stats implements Serializable {
    private Integer target = 2000;
    private Integer deficit = 2000;
    private static Stats instance = null;

    public static Stats getInstance() {
        if (instance == null) {
            instance = new Stats();
        }
        return instance;
    }
    public Stats() {

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
}
