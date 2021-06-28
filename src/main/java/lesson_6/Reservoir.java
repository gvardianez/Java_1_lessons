package lesson_6;

public class Reservoir extends Obstacle{
    private final int length;

    public Reservoir(int length) {
        this.length = length;
    }

    @Override
    public boolean swimAcross(AbleToSwim object) { //Аналогично методу runAcross
    return (object.swim(this.length));
    }

    @Override
    public boolean runAcross(AbleToRun object) {
        return false;
    }
}
