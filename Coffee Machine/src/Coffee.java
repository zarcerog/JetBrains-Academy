public class Coffee {
    private final int WATER;
    private final int MILK;
    private final int COFFEE_BEANS;
    private final int COST;

    public Coffee(int water, int milk, int coffeeBeans, int cost) {
        WATER = water;
        MILK = milk;
        COFFEE_BEANS = coffeeBeans;
        COST = cost;
    }

    public int getWater() {
        return WATER;
    }

    public int getMILK() {
        return MILK;
    }

    public int getCOFFEE_BEANS() {
        return COFFEE_BEANS;
    }

    public int getCOST() {
        return COST;
    }
}
