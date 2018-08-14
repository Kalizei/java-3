package Lesson1.Homework.Task3;

public abstract class Fruit {
    protected float weight;
    protected String type_fruit;

    final String TYPE_ORANGE = "Апельсин";
    final String TYPE_APPLE = "Яблоко";
    final float WEIGHT_ORANGE = 1.5f;
    final float WEIGHT_APPLE = 1.0f;

    public String getType_fruit() {
        return type_fruit;
    }

    public float getWeight() {
        return weight;
    }
}
