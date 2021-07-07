package lesson_7;

public class DogFood implements Edible{

    private int volume;

    public DogFood(int volume) {
        this.volume = volume;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Собачий корм";
    }
}
