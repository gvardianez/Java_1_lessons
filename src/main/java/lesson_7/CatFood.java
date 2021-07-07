package lesson_7;

public class CatFood implements Edible{

    private int volume;

    public CatFood(int volume) {
        this.volume = volume;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Кошачий корм";
    }

}
