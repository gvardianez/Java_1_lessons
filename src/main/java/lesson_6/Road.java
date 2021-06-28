package lesson_6;

public class Road extends Obstacle{
    private final int length;

    public Road(int length) {
        this.length = length;
    }

    @Override
    public boolean runAcross(AbleToRun object) {  //В параметр метода можно поставить лишь только то, что умеет бегать,
        return (object.run(this.length));         //т.е. реаилизует интерфейс AbleToRun, и метод Run соответсвенно, в
    }                                             //в котором есть логика проверки прохождения (пробега здесь) препятствия

    @Override
    public boolean swimAcross(AbleToSwim object) {
        return false;
    }
}
