package lesson_6;

public abstract class Animal implements AbleToSwim,AbleToRun {

    private static int valueAnimal;

    public Animal() {
        valueAnimal++;
    }

    public static int getValue() {
        return valueAnimal;
    }

    public abstract boolean run(int length);

    public abstract boolean swim(int length);

}
