package lesson_7;

public interface WhereCanEat {
    void put(Edible object);
    void pull(Edible object);
    int getFullness();
    int getVolume();
    Edible getSomethingEdible();

}
